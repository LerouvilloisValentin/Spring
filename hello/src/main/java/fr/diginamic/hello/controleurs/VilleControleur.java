package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dao.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
	@GetMapping
	public List<Ville> getVille(){
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Marseille", 861635));
        villes.add(new Ville("Lyon", 513275));

        return villes;
	}
}
