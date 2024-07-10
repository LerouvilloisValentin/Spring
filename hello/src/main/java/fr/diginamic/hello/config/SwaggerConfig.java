package fr.diginamic.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API Recensement").version("1.0")
				.description("Cette API fournit des données de recensement de population pour la France.")
				.termsOfService("OPEN DATA")
				.contact(new Contact().name("Lerouvillois Valentin").email("lerouvillois.valentin@hotmail.com").url("URL du contact")));

	}
}
