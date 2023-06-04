package com.ismail.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ismail.demo.dao.AlienRepo;
import com.ismail.demo.model.Alien;

@Controller
public class AlienController {
	
	Logger logger=LoggerFactory.getLogger(AlienController.class);
	
	@Autowired
	AlienRepo alienRepo;
	
	@RequestMapping("/")
	public String home() {
		//......Testing user defind method......
		//List<Alien> aliens = alienRepo.findByAge(33);
		//List<Alien> aliens = alienRepo.findByAgeGreaterThan(33);
		//List<Alien> aliens = alienRepo.findByAgeLessThan(33);
		List<Alien> aliens = alienRepo.findByAgeSorted(33);
		System.out.println(aliens);
		//.......End.............
		return "home.jsp";
	}
	
	//...Save data into DB.......
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		alienRepo.save(alien);
		return "home.jsp";
	}
	
	//...Get data from DB.......
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int id) {
		Alien alien = alienRepo.findById(id).orElse(new Alien());
		ModelAndView modelAndView = new ModelAndView("showAlien.jsp");
		modelAndView.addObject(alien);
		return modelAndView;
	}
	
	//================REST Calls================
	
	@RequestMapping("/aliens")
	@ResponseBody
	public String getAliens() {
		logger.info("getAliens method called...........");
		return alienRepo.findAll().toString(); //..toString() returns data as object format
	}
	
	@RequestMapping("/alien/{id}")
	@ResponseBody
	public Optional<Alien> alienById(@PathVariable("id") int id) {   //.....Optional<Alen>...it will return data as json format
		return alienRepo.findById(id);
	}

	//@RequestMapping(path="/aliensXML", produces = {"application/json"})  //.. returns data as Json format only
	@GetMapping(path="/aliensXML", produces = "application/xml")   //.. returns data as XML format only
	@ResponseBody
	public List<Alien> getAliensXML() {
		return alienRepo.findAll(); 
	}
	
	//....save data.........
	
	@PostMapping("/saveAlien")
	@ResponseBody
	public Alien saveAlien(@RequestBody Alien alien) {
		alienRepo.save(alien);
		return alien;
	}
	
	//....delete data.........
	
	@DeleteMapping("/deleteAlien/{id}")
	@ResponseBody
	public String deleteAlien(@PathVariable("id") int id) {
		Alien alien = alienRepo.getOne(id);
		alienRepo.delete(alien);
		return "Deleted";
	}
	
	//....update data.........
	
	@PutMapping("/updateAlien")
	@ResponseBody
	public Alien updateAlien(@RequestBody Alien alien) {
		alienRepo.save(alien);
		return alien;
	}
}
