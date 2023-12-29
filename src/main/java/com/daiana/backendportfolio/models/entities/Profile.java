package com.daiana.backendportfolio.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String lastname;
	private String about;
	private String profilePicture;
	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;
	@OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
	private List<Project> projectList = new ArrayList<>();

	public Profile() {
	}

	public Profile(String name, String lastname, String about, String profilePicture, Person person) {
		this.name = name;
		this.lastname = lastname;
		this.about = about;
		this.profilePicture = profilePicture;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	//agregar projecto
	public void addProject(Project project){
		project.setProfile(this);
		this.projectList.add(project);
	}
}
