package tn.esprit.spring.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	ContratRepository contratRepoistory;

	public Contrat ajouterContrat(Contrat contrat) {
		l.info("Let's add a Contract");
		contratRepoistory.save(contrat);
		l.info("Contract added successfully");
		return contrat;
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Optional<Contrat> contratOptional = contratRepoistory.findById(contratId);
		Optional<Employe> employeOptional = employeRepository.findById(employeId);

		Contrat contratManagedEntity = contratOptional.orElse(new Contrat());
		Employe employeManagedEntity = employeOptional.orElse(new Employe());

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);

	}

	public void deleteContratById(int contratId) {
		Optional<Contrat> contratOptional = contratRepoistory.findById(contratId);
		Contrat contratManagedEntity = contratOptional.orElse(new Contrat());
		if (contratManagedEntity.getReference() == 0) {
			l.error("Contract Not Found");
		}
		contratRepoistory.delete(contratManagedEntity);
		l.info("Contract deleted successfully");
	}

	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
		l.info("All Contracts deleted successfully");
	}
}
