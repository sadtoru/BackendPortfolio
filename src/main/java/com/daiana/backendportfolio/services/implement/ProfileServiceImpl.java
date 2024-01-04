package com.daiana.backendportfolio.services.implement;

import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.models.dto.mapper.DtoMapperProfile;
import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.models.entities.Profile;
import com.daiana.backendportfolio.repositories.PersonRepository;
import com.daiana.backendportfolio.repositories.ProfileRepository;
import com.daiana.backendportfolio.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private PersonRepository personRepository;
	@Override
	@Transactional(readOnly = true)
	public List<ProfileDto> findAll() {
		List<Profile> profileList = (List<Profile>) profileRepository.findAll();
		return profileList.stream()
				.map(profile -> DtoMapperProfile.builder().setProfile(profile).build())
				.collect(Collectors.toList());
	}

	@Override
	public boolean hasProfile(String username) {
		Optional<Person> person = personRepository.findByUsername(username);
		return person.isPresent() && person.orElseThrow().getProfileUser() != null;
	}

	@Override
	@Transactional
	public void save(Profile profile, String username) {
		Person person = personRepository.findByUsername(username).orElseThrow();

		person.setProfileUser(profile);
		profile.setPerson(person);

		profileRepository.save(profile);
		personRepository.save(person);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProfileDto> findById(Long id) {
		return profileRepository.findById(id).map(profile -> DtoMapperProfile.builder().setProfile(profile).build());
	}

	@Override
	@Transactional
	public boolean hasProfileOwner(String username, Long id) {
		Optional<Profile> optionalProfile = profileRepository.findById(id);

		return optionalProfile.map(profile -> profile.getPerson().getUsername().equals(username)).orElse(false);
	}

	@Override
	public void remove(Long id) {
		profileRepository.findById(id);
	}

	@Override
	public Profile getProfileByUsername(String username) {
		return profileRepository.findByPersonUsername(username);
	}

	@Override
	@Transactional
	public Optional<ProfileDto> update(Profile profileDto, Long id) {

		Optional<Profile> optionalProfile = profileRepository.findById(id);

		Profile profile = null;

		if(optionalProfile.isPresent()){
			Profile profileDb = optionalProfile.orElseThrow();
			profileDb.setName(profileDto.getName());
			profileDb.setLastname(profileDto.getLastname());
			profileDb.setAbout(profileDto.getAbout());
			profileDb.setProfilePicture(profileDto.getProfilePicture());
			profile = profileRepository.save(profileDb);
		}
		return Optional.ofNullable(DtoMapperProfile.builder().setProfile(profile).build());
	}
}
