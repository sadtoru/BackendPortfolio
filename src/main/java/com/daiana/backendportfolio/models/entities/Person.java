package com.daiana.backendportfolio.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique = true)
	@Size(min = 4, max = 12)
	private String username;
	@NotEmpty
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
