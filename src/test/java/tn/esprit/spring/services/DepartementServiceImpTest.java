package tn.esprit.spring.services;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

@RunWith(SpringRunner.class)
@SpringBootTest


public class DepartementServiceImpTest {
	
	@Autowired
	IEntrepriseService es;
	DepartementRepository depp;
	
	@Test
	public void testAjouterDepartement(){
		Departement dep = new Departement("RH");
		int res = es.ajouterDepartement(dep);			
		assertEquals(dep.getId(), res);
		
	}

}
