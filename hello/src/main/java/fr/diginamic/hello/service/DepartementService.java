package fr.diginamic.hello.service;

import fr.diginamic.hello.entities.Departement;

public class DepartementService {

	private DepartementRepository departementRepository;
//	
//	public Departement insertDepartement(){
//		return (List<Departement>)departementRepository.save();
//	}
	
	public Departement insertDepartements(Departement departement){
		return departementRepository.save(departement);
	}
}
