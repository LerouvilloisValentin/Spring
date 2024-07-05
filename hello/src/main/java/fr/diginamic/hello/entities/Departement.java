package fr.diginamic.hello.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPARTEMENT")
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VILLE")
	private Integer id;
	@Column(name = "NOM")
	private String nom;
	@ManyToOne
	@JoinColumn(name = "VILLE")
	private Ville ville;

	public Departement() {
		super();
	}

	public Departement(Integer id, String nom, Ville ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.ville = ville;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Ville getVille() {
		return ville;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

}
