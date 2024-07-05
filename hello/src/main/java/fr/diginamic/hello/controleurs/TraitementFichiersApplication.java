package fr.diginamic.hello.controleurs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;

@SpringBootApplication
public class TraitementFichiersApplication implements CommandLineRunner {
	@Autowired
	private VilleService villeService;
	private DepartementService departementService;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
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
            System.out.println(departement);

            /**
             * Insérer la ville en base de données
             */
            villeService.insertVille(ville);
            departementService.insertDepartements(departement);

		}
	}
}