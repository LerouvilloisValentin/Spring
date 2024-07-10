package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.diginamic.hello.entities.DepVilleApi;

@Repository
public interface DepVilleApiRepository extends CrudRepository<DepVilleApi,Integer> {

	List<DepVilleApi> findByPopulationGreaterThan(int min);
	

}

