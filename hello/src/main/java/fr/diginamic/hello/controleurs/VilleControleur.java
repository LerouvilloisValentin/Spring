package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dao.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	private List<Ville> villes = new ArrayList<>();

	@GetMapping
	public List<Ville> getVille() {
		villes.add(new Ville("Paris", 2148000));
		villes.add(new Ville("Marseille", 861635));
		villes.add(new Ville("Lyon", 513275));

		return villes;
	}

	@PutMapping("/addVilles")
	public ResponseEntity<String> addVille(@RequestBody Ville nvVille) {
		for (Ville ville : villes) {
			if (ville.getNom().equals(nvVille.getNom())) {
				return ResponseEntity.badRequest().body("La ville existe déjà");
			}
		}
		villes.add(nvVille);
		return ResponseEntity.ok("Ville insérée avec succès");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
		for (Ville ville : villes) {
			if (ville.getId() == id) {
				return new ResponseEntity<>(ville, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/updateVilles")
	public ResponseEntity<String> updateVille(@RequestBody Ville updatedVille) {
		for (Ville ville : villes) {
			if (ville.getId().equals(updatedVille.getId())) {
				ville.setNom(updatedVille.getNom());
				ville.setNbHab(updatedVille.getNbHab());
				return ResponseEntity.ok("Ville mise à jour avec succès");
			}
		}
		return ResponseEntity.badRequest().body("Ville non trouvé");
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
