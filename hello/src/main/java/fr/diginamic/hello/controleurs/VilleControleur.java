package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.service.VilleService;
import jakarta.validation.Valid;

@RestController
@Service
@RequestMapping("/villes")
public class VilleControleur {
	private List<Ville> villes = new ArrayList<>();
	@Autowired
	private VilleService villeService;

	@GetMapping
	public List<Ville> getVille() {
		return villeService.extractVilles();
	}

	@GetMapping("/id/{id}")
	public Ville getVilleById(@PathVariable int id) {
		return villeService.extractVille(id);
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<?> getVilleByName(@PathVariable String nom) {
		List<Ville> ville = villeService.extractVilleByName(nom);
		if (ville == null) {
			return ResponseEntity.badRequest().body("La ville n'existe pas");
		}
		return ResponseEntity.ok(ville);
	}

	@PostMapping("/addVilles")
	public ResponseEntity<String> addVille(@Valid @RequestBody Ville nvVille, BindingResult controleQualite) {
		if (controleQualite.hasErrors()) {
			return ResponseEntity.badRequest()
					.body("l'id n'est pas un entier positif ou le nom ne contient pas au minimuum 3 caractères");
		}

		for (Ville ville : villes) {
			if (ville.getNom().equals(nvVille.getNom())) {
				return ResponseEntity.badRequest().body("La ville existe déjà");
			}
		}
		villeService.insertVille(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès");
	}

	@PutMapping("/updateVilles")
	public ResponseEntity<String> updateVille(@Valid @RequestBody  Ville updatedVille,
			BindingResult controleQualite) {
		if (controleQualite.hasErrors()) {
			return ResponseEntity.badRequest().body(
					"l'id modifié n'est pas un entier positif ou le nom modifié ne contient pas au minimuum 3 caractères");
		}
			int idVille = updatedVille.getId();
			villeService.updateVille(idVille, updatedVille);

			return ResponseEntity.ok("Ville mise à jour avec succès");


//		return ResponseEntity.badRequest().body("Ville non trouvé");
	}

	@DeleteMapping("/deleteVille/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id) {
		for (int i = 0; i < villes.size(); i++) {
			if (villes.get(i).getId() == id) {
				villes.remove(i);
				return ResponseEntity.ok("Ville supprimée avec succès");
			}
		}
		return ResponseEntity.badRequest().body("Ville non trouvée");
	}

}
