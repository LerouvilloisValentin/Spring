package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entities.Ville;

@Service
public class VilleService {
	@Autowired
	private VilleDao villeDao;

	public List<Ville> extractVilles() {
		return villeDao.extractVilles();
	}
	
	public Ville extractVille(int idVille) {
		return villeDao.extractVille(idVille);
	}
	
	public List<Ville> extractVilleByName(String nom) {
		return villeDao.extractVilleByName(nom);
	}
	
	public List<Ville>insertVille(Ville ville){
		return villeDao.insertVille(ville);
	}

	public List<Ville>updateVille(int idVille, Ville villeUpdated){
		return villeDao.updateVille(idVille, villeUpdated);
	}
}
