package com.daiana.backendportfolio.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ProgrammingLanguage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private String logo;
	private String name;
	@ManyToMany(mappedBy = "programmingLanguagesList")
	private List<Project> projectList = new ArrayList<>();

	public ProgrammingLanguage() {
	}

	public ProgrammingLanguage(String name) {
		this.name = name;
	}

	public void addProject(Project project){
		projectList.add(project);
		project.getProgrammingLanguagesList().add(this);
	}


}
