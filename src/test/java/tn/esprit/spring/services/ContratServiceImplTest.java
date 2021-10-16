package tn.esprit.spring.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	@Autowired
	IEmployeService es;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	EmployeRepository employeRepository;
	
	@Test
	public void testAjouterContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse("2015-03-23");
		Contrat contrat = new Contrat(dateDebut, "CIVP", 5.4f);
		int res = es.ajouterContrat(contrat).getReference();
		assertEquals(contrat.getReference(), res);
		es.deleteContratById(contrat.getReference()); 
	}
	
	/*@Test
	public void testAffecterContratAEmploye() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse("2019-03-23");
		Contrat contrat = es.ajouterContrat(new Contrat(dateDebut, "CDD", 5.4f));
		Employe employe = es.ajouterEmploye(new Employe("Malek", "Ajmi", "ajmi.malek@esprit.tn", true, Role.INGENIEUR));
		es.affecterContratAEmploye(contrat.getReference(),employe.getId());
		System.out.println("Employe Contrat "+ contrat.getEmploye().getId());
		assertTrue(employeRepository.existsById(contrat.getEmploye().getId()));
		// delete emp & contrat
		es.deleteEmployeById(employe.getId());
		es.deleteContratById(contrat.getReference());
	}*/
	
	@Test
	public void testDeleteContratById() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse("2017-03-23");
		Contrat contrat = es.ajouterContrat(new Contrat(dateDebut, "CDD", 112.4f));
		es.deleteContratById(contrat.getReference());
		assertFalse(contratRepoistory.existsById(contrat.getReference()));
	}
	
	@Test
	public void testDeleteAllContratJPQL() throws ParseException {
		for (int i = 0; i < 4; i++) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = dateFormat.parse("2017-03-23");
			Contrat contrat = es.ajouterContrat(new Contrat(dateDebut, "CDD", 112.4f));
		}
		es.deleteAllContratJPQL();
		assertArrayEquals(new Object[0],IterableUtil.toArray( contratRepoistory.findAll()));
	}
}
