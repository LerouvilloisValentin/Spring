package fr.diginamic.hello.controleurs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
	@Autowired
	private DepartementService departementService;

	@GetMapping
	public List<DepartementDto> getDepartements(){
		return departementService.getDepartements();
	} 
	
	/**
	 * méthode qui doit ajoute un département à l'appel de la route departements
	 */
//	@PostMapping
//	public Departement insertDepartements(@RequestBody DepartementDto nvDepartement) {
//		for(Departement departement : departements) {
//			return departementService.insertDepartements(nvDepartement);
//		}
//		return nvDepartement;
//	}

}
