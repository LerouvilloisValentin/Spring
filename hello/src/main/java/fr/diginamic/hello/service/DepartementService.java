package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entities.Departement;

@Service
public class DepartementService {
	@Autowired
	private DepartementRepository departementRepository;

	public List<Departement> getDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}
	
    public Departement insertOrUpdateDepartements(Departement departement) {
        Departement existingDepartement = departementRepository.findByCodeDepartement(departement.getCodeDepartement());
        if (existingDepartement != null) {
            return existingDepartement;
        }
        return departementRepository.save(departement);
    }
}
