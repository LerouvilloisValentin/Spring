package fr.diginamic.hello.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPARTEMENT")
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VILLE")
	private Integer id;
	@Column(name = "CODE_DEPARTEMENT")
	private String codeDepartement;
	@Column(name = "NOM")
	private String nom;
	@OneToMany(mappedBy = "departement")
	private Set<Ville> villes= new HashSet<>();

	public Departement() {
		super();
	}

	public Departement(Integer id, String codeDepartement) {
		super();
		this.id = id;
		this.codeDepartement = codeDepartement;
	}

	public Departement(Integer id,String nom,  Set<Ville> villes) {
		super();
		this.id = id;
		this.nom = nom;
		this.villes = villes;
	}


    public void addVille(Ville ville) {
        this.villes.add(ville);
        ville.setDepartement(this);
    }
    
	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Set<Ville> getVilles() {
		return villes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
	}



	
}
