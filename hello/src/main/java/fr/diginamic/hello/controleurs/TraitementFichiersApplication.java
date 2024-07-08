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
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;
import jakarta.transaction.Transactional;

@SpringBootApplication(scanBasePackages = "fr.diginamic.hello")
public class TraitementFichiersApplication implements CommandLineRunner {
	@Autowired
	private VilleService villeService;
	@Autowired
	private DepartementService departementService;
	@Value("${initialisation.base}")
	private boolean initialisationBase;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
//		if(!initialisationBase) {
//			System.out.println("La base est déjà initialisé");
//			return;
//		}
		Path path = Paths.get("C:/code/java/ApprocheObject/recensement.csv");
		List<String> recensement = Files.readAllLines(path);

		recensement.remove(0);

		for (String ligne : recensement) {
			String[] tokens = ligne.split(";");
			System.out.println(ligne);
			
			String name = tokens[6];
			String dpt = tokens[2];
			String poptok = tokens[9].trim().replaceAll(" ", "");
			int population = Integer.parseInt(poptok);
			
	        Ville ville = new Ville();
	        Departement departement = new Departement();
            ville.setNom(name);
            ville.setPopulation(population);
//            ville.setDepartement(departement);
            departement.setCodeDepartement(dpt);
            departement.addVille(ville);
            
            // Insérer ou mettre à jour le département
	        Departement existingDepartement = departementService.insertOrUpdateDepartements(departement);
	        ville.setDepartement(existingDepartement);
	        
	        /**
	         * Associer la ville au département existant
	         */
//	        existingDepartement.addVille(ville);
	        villeService.insertVille(ville);
            /**
             * Insérer la ville en base de données
             */
//            departementService.insertDepartements(departement);
            

		}
	}
}