package fr.diginamic.hello.dao;

import java.util.List;
import fr.diginamic.hello.entities.Departement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DepartementDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Departement> extractDepartements(){
		return em.createQuery("FROM Departement",Departement.class).getResultList();
		
	}
}
