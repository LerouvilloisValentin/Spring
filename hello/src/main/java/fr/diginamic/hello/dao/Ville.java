package fr.diginamic.hello.dao;

public class Ville {
	protected Integer id;
	protected String nom;
	protected Integer nbHab;

	public Ville(String nom, Integer nbHab) {
		super();
		this.nom = nom;
		this.nbHab = nbHab;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public Integer getNbHab() {
		return nbHab;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNbHab(Integer nbHab) {
		this.nbHab = nbHab;
	}

}
