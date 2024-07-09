package fr.diginamic.hello.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import fr.diginamic.hello.dto.VilleDto;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.entities.Departement;

@Service
public class DTOService {

    public VilleDto convertToVilleDTO(Ville ville) {
        VilleDto villeDTO = new VilleDto();
        villeDTO.setId(ville.getId());
        villeDTO.setNom(ville.getNom());
        if(ville.getPopulation() != null) {
        villeDTO.setPopulation(ville.getPopulation());
        }
        return villeDTO;
    }

    public Ville convertToVilleEntity(VilleDto villeDTO) {
        Ville ville = new Ville();
        ville.setId(villeDTO.getId());
        ville.setNom(villeDTO.getNom());
        ville.setPopulation(villeDTO.getPopulation());
        return ville;
    }

    public DepartementDto convertToDepartementDTO(Departement departement) {
        DepartementDto departementDTO = new DepartementDto();
        departementDTO.setId(departement.getId());
        departementDTO.setCodeDepartement(departement.getCodeDepartement());
        departementDTO.setNom(departement.getNom());
        List<VilleDto> villeDTOs = departement.getVilles().stream()
                .map(this::convertToVilleDTO)
                .collect(Collectors.toList());
        departementDTO.setVilles(villeDTOs);
        return departementDTO;
    }

    public Departement convertToDepartementEntity(DepartementDto departementDTO) {
        Departement departement = new Departement();
        departement.setId(departementDTO.getId());
        departement.setCodeDepartement(departementDTO.getCodeDepartement());
        departement.setNom(departementDTO.getNom());
        Set<Ville> villes = departementDTO.getVilles().stream()
                .map(this::convertToVilleEntity)
                .collect(Collectors.toSet());
        departement.setVilles(villes);
        return departement;
    }
}
