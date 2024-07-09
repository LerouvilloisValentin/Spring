package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.service.VilleService;
import jakarta.validation.Valid;

@RestController
@Service
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<VilleDto> getVille() {
        return villeService.getVilles();
    }

    @GetMapping("/id/{id}")
    public VilleDto getVilleById(@PathVariable int id) {
        return villeService.getVille(id);
    }

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
