package com.daiana.backendportfolio.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Framework {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private String logo;
	private String name;
	@ManyToMany(mappedBy = "frameworkList")
	private Set<Project> projectList = new HashSet<>();

	public Framework() {
	}

	public Framework(String name) {
		this.name = name;
	}
}
