package com.daiana.backendportfolio.services.implement;

import com.daiana.backendportfolio.models.dto.ProjectDto;
import com.daiana.backendportfolio.models.dto.mapper.DtoMapperProject;
import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.models.entities.Project;
import com.daiana.backendportfolio.models.request.ProjectRequest;
import com.daiana.backendportfolio.repositories.PersonRepository;
import com.daiana.backendportfolio.repositories.ProfileRepository;
import com.daiana.backendportfolio.repositories.ProjectRepository;
import com.daiana.backendportfolio.services.ProjectService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private PersonRepository personRepository;
	@Override
	@Transactional
	public List<ProjectDto> getAllProjectsByUsername(String username) {
		Person person = personRepository.findByUsername(username).orElseThrow(() -> new EntityExistsException("Usuario no encontrado"));

		return person.getProfileUser().getProjectList().stream().map
				(project -> DtoMapperProject.builder().setProject(project).build())
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public boolean hasProjectOwner(String username, Long id) {
		Optional<Project> optionalProject = projectRepository.findById(id);

		return optionalProject.map(project -> project.getProfileProject().getPerson().getUsername().equals(username)).orElse(false);
	}

	@Override
	@Transactional
	public void save(Project project, String username) {
		Person person = personRepository.findByUsername(username).orElseThrow();

		person.getProfileUser().addProject(project);

		projectRepository.save(project);
	}

	@Override
	public void update(ProjectRequest project, Long id) {
		Optional<Project> optionalProject = projectRepository.findById(id);
		Project oProject = null;
		if(optionalProject.isPresent()){
			oProject = optionalProject.orElseThrow();
			oProject.setProjectName(project.getProjectName());
			oProject.setDescription(project.getDescription());
			oProject.setTech(project.getTech());
			oProject.setGithubUrl(project.getGithubUrl());
			oProject.setDeployUrl(project.getDeployUrl());

			projectRepository.save(oProject);
		}
	}

	@Override
	public void remove(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Optional<ProjectDto> findById(Long id) {
		return projectRepository.findById(id).map(project -> DtoMapperProject.builder().setProject(project).build());
	}


}
