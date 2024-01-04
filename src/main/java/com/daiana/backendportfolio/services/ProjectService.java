package com.daiana.backendportfolio.services;

import com.daiana.backendportfolio.models.dto.ProjectDto;
import com.daiana.backendportfolio.models.entities.Project;
import com.daiana.backendportfolio.models.request.ProjectRequest;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
	List<ProjectDto> getAllProjectsByUsername(String username);
	boolean hasProjectOwner(String username, Long id);
	void save(Project project, String username);
	void update(ProjectRequest project, Long id);
	void remove(Long id);
	Optional<ProjectDto> findById(Long id);
}
