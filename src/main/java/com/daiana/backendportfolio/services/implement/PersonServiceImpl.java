package com.daiana.backendportfolio.services.implement;

import com.daiana.backendportfolio.models.dto.PersonDto;
import com.daiana.backendportfolio.models.dto.mapper.DtoMapperPerson;
import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.repositories.PersonRepository;
import com.daiana.backendportfolio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional(readOnly = true)
	public List<PersonDto> findAll() {
		List<Person> personList = (List<Person>) personRepository.findAll();
		return personList.stream()
				.map(person -> DtoMapperPerson.builder().setPerson(person).build())
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PersonDto> findById(Long id) {
		return personRepository.findById(id)
				.map(person -> DtoMapperPerson.builder().setPerson(person).build());
	}

	@Override
	@Transactional
	public Person save(Person person) {
		//TODO
		//password
		//rol
		return personRepository.save(person);

	}

	@Override
	@Transactional
	public void remove(Long id) {
		personRepository.deleteById(id);
	}
}
