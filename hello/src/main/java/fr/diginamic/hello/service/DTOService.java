package fr.diginamic.hello.service;


import org.springframework.stereotype.Service;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entities.Ville;

@Service
public class DTOService {

    public VilleDto convertToDTO(Ville ville) {
        VilleDto villeDTO = new VilleDto();
        villeDTO.setId(ville.getId());
        villeDTO.setNom(ville.getNom());
        villeDTO.setPopulation(ville.getPopulation());
        return villeDTO;
    }

    public Ville convertToEntity(VilleDto villeDTO) {
        Ville ville = new Ville();
        ville.setId(villeDTO.getId());
        ville.setNom(villeDTO.getNom());
        ville.setPopulation(villeDTO.getPopulation());
        return ville;
    }


}
