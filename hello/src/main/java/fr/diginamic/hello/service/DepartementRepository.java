package fr.diginamic.hello.service;

import org.springframework.data.repository.CrudRepository;
import fr.diginamic.hello.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

	Departement findByCodeDepartement(String codeDepartement);
}
