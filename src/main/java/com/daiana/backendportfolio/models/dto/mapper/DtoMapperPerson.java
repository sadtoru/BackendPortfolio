package com.daiana.backendportfolio.models.dto.mapper;

import com.daiana.backendportfolio.models.dto.PersonDto;
import com.daiana.backendportfolio.models.entities.Person;

public class DtoMapperPerson {
	private Person person;

	private DtoMapperPerson(){
	}

	public static DtoMapperPerson builder(){
		return new DtoMapperPerson();
	}

	public DtoMapperPerson setPerson( Person person){
		this.person = person;
		return this;
	}

	public PersonDto build(){
		if(person == null){
			throw new RuntimeException("No se proporciono la entidad Persona");
		}
		return new PersonDto(this.person.getId(), this.person.getUsername());
	}
}
