package com.daiana.backendportfolio.services;

import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.models.entities.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
	List<ProfileDto> findAll();
	boolean hasProfile(String username);
	void save(Profile profile, String username);
	Optional<ProfileDto> findById(Long id);
	boolean hasProfileOwner(String username, Long id);
	void remove(Long id);
	Profile getProfileByUsername(String username);
	Optional<ProfileDto> update(Profile profile, Long id);
}
