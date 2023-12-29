package com.daiana.backendportfolio.repositories;

import com.daiana.backendportfolio.models.entities.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Long> {
}
