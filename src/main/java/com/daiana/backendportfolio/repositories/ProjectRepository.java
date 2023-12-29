package com.daiana.backendportfolio.repositories;

import com.daiana.backendportfolio.models.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
