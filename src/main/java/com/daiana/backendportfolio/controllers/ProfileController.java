package com.daiana.backendportfolio.controllers;

import com.daiana.backendportfolio.models.dto.PersonDto;
import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.models.entities.Profile;
import com.daiana.backendportfolio.services.ProfileService;
import com.daiana.backendportfolio.services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
@CrossOrigin(originPatterns = "*")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	private final ValidationService validationService;

	public ProfileController(ValidationService validationService) {
		this.validationService = validationService;
	}

	@GetMapping
	public List<ProfileDto> getAllProfiles(){
		return profileService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProfile(@PathVariable Long id){
		Optional<ProfileDto> profileDto = profileService.findById(id);
		if(profileDto.isPresent()){
			return ResponseEntity.ok(profileDto.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> createProfile(@Valid @RequestBody Profile profile, BindingResult result, Authentication authentication){
		if(result.hasErrors()){
			return validationService.handleValidationErrors(result);
		}
		String username = ((String) authentication.getPrincipal());

		if(profileService.hasProfile(username)){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(String.format("El usuario %s ya tiene un perfil", username));
		}

		profileService.save(profile, username);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
