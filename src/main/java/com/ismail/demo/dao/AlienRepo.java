package com.ismail.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ismail.demo.model.Alien;
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public interface AlienRepo extends JpaRepository<Alien, Integer> {
	
	List<Alien> findByAge(int age);
	
	List<Alien> findByAgeGreaterThan(int age);
	
	List<Alien> findByAgeLessThan(int age);
	
	@Query("from Alien where age=?1 order by name")
	List<Alien> findByAgeSorted(int age);
}
