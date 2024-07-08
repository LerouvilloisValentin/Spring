package fr.diginamic.hello.controleurs;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.service.DepartementService;

@RestController
@Service
@RequestMapping("/departements")
public class DepartementControleur {
	protected List<Departement> departements;
	@Autowired
	private DepartementService departementService;

	
	@GetMapping
	public List<Departement> getDepartements(){
		return departementService.getDepartements();
	} 
	
	@PostMapping("/addDepartement")
	public Departement insertDepartements(@RequestBody Departement nvDepartement) {
		for(Departement departement : departements) {
			return departementService.insertOrUpdateDepartements(nvDepartement);
		}
		return nvDepartement;
	}

}
