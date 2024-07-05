package fr.diginamic.hello.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import fr.diginamic.hello.entities.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class VilleDao {
	@PersistenceContext
	private EntityManager em;
	
	public List<Ville> extractVilles(){
		return em.createQuery("FROM Ville", Ville.class).getResultList();
	}
	
	public Ville extractVille(int idVille) {
		return em.find(Ville.class,idVille);
	}
	
	public List<Ville> extractVilleByName(String nom) {
	    String jpql = "SELECT v FROM Ville v WHERE v.nom = :nom";
	    TypedQuery<Ville> query = em.createQuery(jpql, Ville.class);
	    query.setParameter("nom", nom);
	    return query.getResultList();
	}
	
	@Transactional
    public List<Ville> insertVille(Ville ville) {
    	em.merge(ville);
    	return extractVilles();
    }
	
	@Transactional
	public List<Ville> updateVille(int idVille, Ville villeUpdated){
		Ville ville = em.find(Ville.class, idVille);
		if(ville != null) {
			ville.setNom(villeUpdated.getNom());
			ville.setPopulation(villeUpdated.getPopulation());
			em.merge(ville);
		}
		return extractVilles();
	}
	
	@Transactional
	public List<Ville> deleteVille(int idVille){
		Ville ville = em.find(Ville.class, idVille);
		if(ville !=null) {
			em.remove(ville);
		}
		return extractVilles();
	}
}
