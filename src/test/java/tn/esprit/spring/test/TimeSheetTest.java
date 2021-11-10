package tn.esprit.spring.test;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.TimesheetServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetTest {
	
	private static final Logger l = LogManager.getLogger(TimeSheetTest.class);

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	EntrepriseServiceImpl entrepriseService;
	@Autowired
	TimesheetServiceImpl timesheetService;
	public static int dep,mis;
	
	@Before
	public void affecterParametre(){
		
			
		Departement dep1 = new Departement("dev");
		Mission mission1 = new Mission("mission1");
		// Act 
		dep = entrepriseService.ajouterDepartement(dep1);
		mis = timesheetService.ajouterMission(mission1);
		l.info("id of the departement 1 (dept1) : " + dep1.getId());
		l.info("id of the mission added : " + mis);
	}
	
	
	@Test
	public void affecterMissionADepTest(){
		// Assert
		timesheetService.affecterMissionADepartement(mis,dep);
	}
	
	@After
	public void deleteData(){
		System.out.println("here2");
		// delete data
		
		entrepriseService.deleteDepartementById(dep);
	}

}
