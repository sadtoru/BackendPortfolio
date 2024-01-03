package com.daiana.backendportfolio;

import com.daiana.backendportfolio.models.entities.*;
import com.daiana.backendportfolio.models.enums.ExperienceType;
import com.daiana.backendportfolio.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendportfolioApplication {
	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BackendportfolioApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PersonRepository personRepository, RoleRepository roleRepository,
											   ProfileRepository profileRepository, ProjectRepository projectRepository,
											   ExperienceRepository experienceRepository,
											   FrameworkRepository frameworkRepository){
		return args -> {

			Role roleAdmin = new Role("ROLE_ADMIN");
			Role roleUser = new Role("ROLE_USER");

			roleRepository.save(roleAdmin);
			roleRepository.save(roleUser);

			Person person = new Person("admin", passwordEncoder.encode("asd123"));
			person.getRoles().add(roleAdmin);

			personRepository.save(person);

			//Profile profile = new Profile("admin","admin", "sofi123","asd", person);
			//profileRepository.save(profile);
			/*
			Project project = new Project("tetris","tetris", "www.url.com", "www.deprloy.com", profile);
			profile.addProject(project);
			projectRepository.save(project);
			Experience experience = new Experience("mula","de la casa", LocalDate.now(),LocalDate.now().plusYears(5), ExperienceType.WORK_EXPERIENCE,profile);
			experienceRepository.save(experience);
			Project project1 = new Project("calcu", "v2", "dsd.com", "dsdf.com", profile);
			ProgrammingLanguage language1 = new ProgrammingLanguage("vue");
			ProgrammingLanguage language2 = new ProgrammingLanguage("java");
			project.getProgrammingLanguagesList().add(language1);
			project1.getProgrammingLanguagesList().add(language2);
			language1.getProjectList().add(project);
			language2.getProjectList().add(project1);
			languageRepository.save(language1);
			languageRepository.save(language2);
			projectRepository.save(project);
			projectRepository.save(project1);
			//pruebas
*/

		};
	}
}
