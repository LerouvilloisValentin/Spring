package fr.diginamic.hello.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "VILLE")
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VILLE")
	protected Integer id;

	@Column(name = "NOM")
	protected String nom;

	@Column(name = "POPULATION")
	protected Integer population;

	@ManyToOne
	@JoinColumn(name = "ID_DEPARTEMENT")
	private Departement departement;
	
	public Ville() {
	}

	public Ville(String nom, Integer population) {
		super();
		this.nom = nom;
		this.population = population;

	}

	public Integer getPopulation() {
		return population;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
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



	public void setNom(String nom) {
		this.nom = nom;
	}



}
