package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fr.diginamic.hello.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

	List<Departement> findByCodeDepartement(String codeDepartement);
	List<Departement> findBy();
	
	
}
