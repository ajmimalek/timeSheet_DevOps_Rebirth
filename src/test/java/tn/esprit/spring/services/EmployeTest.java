package tn.esprit.spring.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.DepartementRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {

	@Autowired
	IEmployeService es;
	@Autowired
	DepartementRepository depRepo;
	
	@Test
	public void testAjouterEmployer() throws ParseException {

		int e= es.ajouterEmploye(new Employe("Hadidi", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		assertEquals("Mahmoud", es.getEmployePrenomById(e));
		es.deleteEmployeById(e);
	}

	/*@Test
	public void testGetEmployerPrenomById() throws ParseException {

		assertEquals("Mahmoud", es.getEmployePrenomById(1));

	}
*/
	@Test
	public void testdeletetEmployerById() throws ParseException {

		int e= es.ajouterEmploye(new Employe("HADIDI", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		assertEquals("Mahmoud", es.getEmployePrenomById(e));
		es.deleteEmployeById(e);

	}

/*	@Test
	public void TestgetNombreEmployeJPQL() throws ParseException {
		int e= es.ajouterEmploye(new Employe("HADIDI", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT));
		assertEquals(e, es.getNombreEmployeJPQL());
		es.deleteEmployeById(e);
	}
*/
	@Test
	public void TestgetAllEmployes() throws ParseException {

		es.getAllEmployes();

	}
	/*@Test
	public void affecterEmployeADepartement() throws ParseException {

		Employe employe = new Employe("Hadidi", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT);

		int e = es.ajouterEmploye(employe);

		assertThat(e).isGreaterThan(0);

		Departement departement = new Departement("dep1");

		Departement savedDep = depRepo.save(departement);

		assertThat(savedDep.getId()).isGreaterThan(0);

		List<Employe> Lemp = es.affecterEmployeADepartement(e, savedDep.getId());

		assertThat(Lemp.contains(e)).isTrue();	
		es.deleteEmployeById(e);

	}*/
}
