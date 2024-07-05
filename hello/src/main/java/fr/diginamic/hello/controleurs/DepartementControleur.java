package fr.diginamic.hello.controleurs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	@Autowired
	private DepartementService departementService;

	@PostMapping("/addDepartement")
	public Departement insertDepartements(@RequestBody Departement nvDepartement) {
		return departementService.insertDepartements(nvDepartement);
	}

}
