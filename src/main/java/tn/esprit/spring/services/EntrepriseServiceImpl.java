package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Optional<Departement> d = deptRepoistory.findById(depId);
		Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);
		Entreprise entrepriseManagedEntity = new Entreprise();
		Departement depManagedEntity = new Departement();
		if (d.isPresent() && e.isPresent()) {
			depManagedEntity = d.get();
			entrepriseManagedEntity = e.get();
		}
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> eME = entrepriseRepoistory.findById(entrepriseId);
		Entreprise entrepriseManagedEntity = new Entreprise();
		if (eME.isPresent()) {
			entrepriseManagedEntity = eME.get();

		}	
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);

		if (e.isPresent()) {

			entrepriseRepoistory.delete(e.get());
		}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional<Departement> d = deptRepoistory.findById(depId);

		if (d.isPresent()) {

			deptRepoistory.delete(d.get());
		}	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);

		if (e.isPresent()) {

			return e.get();

		}
		return null;	
	}

}
