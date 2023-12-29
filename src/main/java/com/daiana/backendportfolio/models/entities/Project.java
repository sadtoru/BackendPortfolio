package com.daiana.backendportfolio.models.entities;

import jakarta.persistence.*;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String projectName;
	private String description;
	private String githubUrl;
	private String deployUrl;
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public Project() {
	}

	public Project(String projectName, String description, String githubUrl, String deployUrl, Profile profile) {
		this.projectName = projectName;
		this.description = description;
		this.githubUrl = githubUrl;
		this.deployUrl = deployUrl;
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getDeployUrl() {
		return deployUrl;
	}

	public void setDeployUrl(String deployUrl) {
		this.deployUrl = deployUrl;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
