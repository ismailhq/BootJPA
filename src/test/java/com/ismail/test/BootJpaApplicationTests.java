package com.ismail.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ismail.demo.dao.AlienRepo;
import com.ismail.demo.model.Alien;

@RunWith(SpringRunner.class)
@SpringBootTest
class BootJpaApplicationTests {
	
	@MockBean
	AlienRepo alienRepo;

	@Test
	public void findAllTest() {
		when(alienRepo.findAll()).thenReturn(Stream.of(new Alien(1,"Ismail",32),new Alien(2,"Amina",31)).collect(Collectors.toList()));
		assertEquals(2, alienRepo.findAll().size());
	}

}
