package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.service.VilleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@Service
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @Operation(summary = "Renvoie la liste de ville entière")
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200",
			       description = "Retourne la ville les villes et leurs populations",
			       content = { @Content(mediaType = "application/json",
			       schema = @Schema(implementation = VilleDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Si l'url n'est pas bon",
	    			content = @Content)})
    @GetMapping
    public List<VilleDto> getVille() {
        return villeService.getVilles();
    }

    @GetMapping("/id/{id}")
    public VilleDto getVilleById(@PathVariable int id) {
        return villeService.getVille(id);
    }
    /*
     * Get /nom/(nom de la ville) permet de rechercher par le nom de la ville
     */
    @Operation(summary = "Recherche d'une ville par le nom")
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200",
			       description = "Retourne la ville indiquer dans l'url et sa population",
			       content = { @Content(mediaType = "application/json",
			       schema = @Schema(implementation = VilleDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Si le nom renseigné n'existe pas",
	    			content = @Content)})
    @GetMapping("/nom/{nom}")
    public ResponseEntity<?> getVilleByName(@PathVariable String nom) {
        List<VilleDto> ville = villeService.extractVilleByName(nom);
        if (ville == null) {
            return ResponseEntity.badRequest().body("La ville n'existe pas");
        }
        return ResponseEntity.ok(ville);
    }

    @PostMapping("/addVilles")
    public ResponseEntity<String> addVille(@Valid @RequestBody VilleDto nvVille, BindingResult controleQualite) {
        if (controleQualite.hasErrors()) {
            return ResponseEntity.badRequest().body("L'id n'est pas un entier positif ou le nom ne contient pas au minimum 3 caractères");
        }

        List<VilleDto> villes = villeService.getVilles();
        for (VilleDto ville : villes) {
            if (ville.getNom().equals(nvVille.getNom())) {
                return ResponseEntity.badRequest().body("La ville existe déjà");
            }
        }

        villeService.insertVille(nvVille);
        return ResponseEntity.ok("Ville insérée avec succès");
    }

    @PutMapping("/updateVilles")
    public ResponseEntity<String> updateVille(@Valid @RequestBody VilleDto updatedVille, BindingResult controleQualite) {
        if (controleQualite.hasErrors()) {
            return ResponseEntity.badRequest().body("L'id modifié n'est pas un entier positif ou le nom modifié ne contient pas au minimum 3 caractères");
        }
        int idVille = updatedVille.getId();
        villeService.updateVille(idVille, updatedVille);
        return ResponseEntity.ok("Ville mise à jour avec succès");
    }

    @DeleteMapping("/deleteVille/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        VilleDto ville = villeService.getVille(id);
        if (ville != null) {
            villeService.deleteVille(id);
            return ResponseEntity.ok("Ville supprimée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Ville introuvable");
        }
    }

    @GetMapping("/search")
    public List<VilleDto> getVilleByFirstCaract(@RequestParam String nom) {
        return villeService.findByNomStartingWith(nom);
    }

    @GetMapping("/searchbypop")
    public List<VilleDto> getVilleByPopSup(@RequestParam int min) {
        return villeService.popSuperieurA(min);
    }

    @GetMapping("/searchbypopslice")
    public List<VilleDto> getVilleByPopSlice(@RequestParam int min, @RequestParam int max) {
        return villeService.popEntreMinMax(min, max);
    }
}
