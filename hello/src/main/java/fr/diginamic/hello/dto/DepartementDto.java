package fr.diginamic.hello.dto;

import java.util.List;

public class DepartementDto {
    private Integer id;
    private String codeDepartement;
    private String nom;
    private List<VilleDto> villes;

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<VilleDto> getVilles() {
        return villes;
    }

    public void setVilles(List<VilleDto> villes) {
        this.villes = villes;
    }
}
