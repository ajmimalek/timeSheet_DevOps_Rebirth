package tn.esprit.spring.services;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {

	@Autowired
	IEmployeService es;

	@Test
	public void testAjouterEmployer() throws ParseException {

		int e= es.ajouterEmploye(new Employe("HADIDI", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		assertEquals("Mahmoud", es.getEmployePrenomById(e));
		es.deleteEmployeById(e);
	}

	@Test
	public void testGetEmployerPrenomById() throws ParseException {

		assertEquals("Mahmoud", es.getEmployePrenomById(1));

	}

	@Test
	public void testdeletetEmployerById() throws ParseException {

		int e= es.ajouterEmploye(new Employe("HADIDI", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		assertEquals("Mahmoud", es.getEmployePrenomById(e));
		es.deleteEmployeById(e);

	}

	@Test
	public void TestgetNombreEmployeJPQL() throws ParseException {
		int e= es.ajouterEmploye(new Employe("HADIDI", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		assertEquals(e, es.getNombreEmployeJPQL());
		es.deleteEmployeById(e);
	}

	@Test
	public void TestgetAllEmployes() throws ParseException {

		es.getAllEmployes();

	}
}
