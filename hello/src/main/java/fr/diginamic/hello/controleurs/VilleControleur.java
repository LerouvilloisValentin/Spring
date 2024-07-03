package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dao.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	private List<Ville> villes = new ArrayList<>();
	@GetMapping
	public List<Ville> getVille(){
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Marseille", 861635));
        villes.add(new Ville("Lyon", 513275));

        return villes;
	}
	
	@PostMapping("/addVilles")
    public ResponseEntity<String> addVille(@RequestBody Ville nvVille) {
        for (Ville ville : villes) {
            if (ville.getNom().equals(nvVille.getNom())) {
                return new ResponseEntity<>("La ville existe déjà", HttpStatus.CONFLICT);
            }
        }
        villes.add(nvVille);
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
    }
}
