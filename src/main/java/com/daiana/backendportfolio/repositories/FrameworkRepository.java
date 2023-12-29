package com.daiana.backendportfolio.repositories;

import com.daiana.backendportfolio.models.entities.Framework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameworkRepository extends CrudRepository<Framework, Long> {
}
