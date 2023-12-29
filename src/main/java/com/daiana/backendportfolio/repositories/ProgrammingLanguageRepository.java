package com.daiana.backendportfolio.repositories;

import com.daiana.backendportfolio.models.entities.ProgrammingLanguage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammingLanguageRepository extends CrudRepository<ProgrammingLanguage, Long> {
}
