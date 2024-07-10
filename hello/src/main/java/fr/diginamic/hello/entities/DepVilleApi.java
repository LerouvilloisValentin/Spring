package fr.diginamic.hello.entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class DepVilleApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nomVille;
	private Integer population;
	private String codeDepartement;
	private String nomDepartement;

	
	public DepVilleApi() {
		
	}
	
	public DepVilleApi(String nomVille, Integer population, String codeDepartement, String nomDepartement) {
		super();
		this.nomVille = nomVille;
		this.population = population;
		this.codeDepartement = codeDepartement;
		this.nomDepartement = nomDepartement;
	}

	public String getNomVille() {
		return nomVille;
	}

	public Integer getPopulation() {
		return population;
	}

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

}
