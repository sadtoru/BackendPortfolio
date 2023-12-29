package com.daiana.backendportfolio;

import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.models.entities.Profile;
import com.daiana.backendportfolio.models.entities.Project;
import com.daiana.backendportfolio.repositories.ProfileRepository;
import com.daiana.backendportfolio.repositories.PersonRepository;
import com.daiana.backendportfolio.repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendportfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendportfolioApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PersonRepository personRepository,
											   ProfileRepository profileRepository, ProjectRepository projectRepository){
		return args -> {
			Person person = new Person("sofiuwu","asd123");
			personRepository.save(person);
			Profile profile = new Profile("sofi","uwu", "sofi123","asd", person);
			profileRepository.save(profile);
			Project project = new Project("tetris","tetris", "www.url.com", "www.deprloy.com", profile);
			profile.addProject(project);
			projectRepository.save(project);
		};
	}
}
