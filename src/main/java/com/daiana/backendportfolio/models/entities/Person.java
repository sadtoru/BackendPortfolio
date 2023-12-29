package com.daiana.backendportfolio.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Profile profileUser;

	public Person() {
	}

	public Person(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
