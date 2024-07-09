package fr.diginamic.hello.controleurs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.service.DTOService;
import fr.diginamic.hello.service.DepartementRepository;
import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;
import jakarta.transaction.Transactional;

@SpringBootApplication(scanBasePackages = "fr.diginamic.hello")
public class TraitementFichiersApplication implements CommandLineRunner {
	@Autowired
	private VilleService villeService;
	@Autowired
	private DepartementService departementService;
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private DTOService dtoService;
	@Value("${initialisation.base}")
	private boolean initialisationBase;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if(!initialisationBase) {
			System.out.println("La base est déjà initialisé");
			return;
		}
		Path path = Paths.get("C:/code/java/ApprocheObject/recensement.csv");
		List<String> recensement = Files.readAllLines(path);

		recensement.remove(0);

		for (String ligne : recensement) {
			String[] tokens = ligne.split(";");

			String name = tokens[6];
			String dpt = tokens[2];
			String poptok = tokens[9].trim().replaceAll(" ", "");
			int population = Integer.parseInt(poptok);

			Ville ville = new Ville();
			Departement departement = new Departement();
			ville.setNom(name);
			ville.setPopulation(population);
			departement.setCodeDepartement(dpt);
				departement.addVille(ville);

			// Insérer ou mettre à jour le département
			DepartementDto departementDto = dtoService.convertToDepartementDTO(departement);
			/*
			 * la métohde récupère en paramètre une Departement et l'insère en bdd
			 */
			departementService.insertDepartements(departementDto);

			VilleDto villeDto = dtoService.convertToVilleDTO(ville);

			// Insérer la ville en utilisant le DTO
			villeService.insertVille(villeDto);


		}
	}
}