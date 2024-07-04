package fr.diginamic.hello.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@NotNull
	@Size(min = 3)
	@Column(name = "NOM")
	protected String nom;

	@Min(1)
	@Column(name = "NB_HAB")
	protected Integer nbHab;

	public Ville() {
	}

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
