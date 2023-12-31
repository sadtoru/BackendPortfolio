package com.daiana.backendportfolio.services;

import com.daiana.backendportfolio.models.dto.PersonDto;
import com.daiana.backendportfolio.models.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PersonService {
	List<PersonDto> findAll();
	PersonDto save(Person person);
	Optional<PersonDto> findById(Long id);
	void remove(Long id);
}
