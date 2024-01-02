package com.daiana.backendportfolio.services.implement;

import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.models.dto.mapper.DtoMapperPerson;
import com.daiana.backendportfolio.models.dto.mapper.DtoMapperProfile;
import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.models.entities.Profile;
import com.daiana.backendportfolio.repositories.ProfileRepository;
import com.daiana.backendportfolio.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	@Override
	public List<ProfileDto> findAll() {
		List<Profile> profileList = (List<Profile>) profileRepository.findAll();
		return profileList.stream()
				.map(profile -> DtoMapperProfile.builder().setProfile(profile).build())
				.collect(Collectors.toList());
	}

	@Override
	public ProfileDto save(Profile profile) {
		return null;
	}

	@Override
	public Optional<ProfileDto> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public void remove(Long id) {

	}
}
