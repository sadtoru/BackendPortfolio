package com.daiana.backendportfolio.repositories;

import com.daiana.backendportfolio.models.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
