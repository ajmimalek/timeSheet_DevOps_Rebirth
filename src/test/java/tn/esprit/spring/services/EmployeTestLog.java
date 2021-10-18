package tn.esprit.spring.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.DepartementRepository;

@Component
@Aspect
public class EmployeTestLog {
	@Autowired
	EmployeServiceImpl empService;
	@Autowired
	DepartementRepository depRepo;
	private static final Logger l = LogManager.getLogger(EmployeTestLog.class);

	@Test
	public void AffecterEmpDeptest() {
		Employe employe = new Employe("Hadidi", "Mahmoud", "mahmoud@esprit.tn", true, Role.CHEF_DEPARTEMENT);

		int savedemp = empService.ajouterEmploye(employe);

		assertThat(savedemp).isGreaterThan(0);

		Departement departement = new Departement("dep1");

		Departement savedDep = depRepo.save(departement);

		assertThat(savedDep.getId()).isGreaterThan(0);

		List<Employe> Lemp = empService.affecterEmployeADepartement(savedemp, savedDep.getId());

		assertThat(Lemp.contains(savedemp)).isTrue();	
	}

	@Around("execution(* tn.esprit.spring.services.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		String name = pjp.getSignature().getName();

		if(elapsedTime>3000){
		l.warn("methode:"+name+ "execution time more than 3 seconds");
		
		}
		return obj;

	}
}

