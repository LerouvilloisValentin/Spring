package fr.diginamic.hello.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entities.Ville;
import jakarta.transaction.Transactional;

@Service
public class VilleService {
	@Autowired
	private VilleDao villeDao;
	@Autowired
	private VilleRepository villeRepository;


	public List<Ville> extractVilleByName(String nom) {
		return villeDao.extractVilleByName(nom);
	}

	public List<Ville> updateVille(int idVille, Ville villeUpdated) {
		return villeDao.updateVille(idVille, villeUpdated);
	}

	public List<Ville> deleteVille(int idVille) {
		return villeDao.deleteVille(idVille);
	}
	/**
	 *méthode avec le CrudRepository
	 */
	public List<Ville> findByNomStartingWith(String nom) {
		return villeRepository.findByNomStartingWith(nom);
	}
	
	public Ville extractVille(int idVille) {
		return villeRepository.findById(idVille).orElse(null);
	}
	@Transactional
	public Ville insertVille(Ville ville) {
		return villeRepository.save(ville);
	}

	public List<Ville> extractVilles() {
		return (List<Ville>) villeRepository.findAll();
	}
	
	public List<Ville> popSuperieurA(int min){
		return (List<Ville>) villeRepository.findByPopulationGreaterThan(min);
	}
	
	public List<Ville> popEntreMinMax(int min, int max){
		return (List<Ville>) villeRepository.findByPopulationBetween(min,max);
	}
}
