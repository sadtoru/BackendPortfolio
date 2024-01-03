package com.daiana.backendportfolio.controllers;

import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.models.dto.mapper.DtoMapperProfile;
import com.daiana.backendportfolio.models.entities.Profile;
import com.daiana.backendportfolio.services.ProfileService;
import com.daiana.backendportfolio.services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

/*	@GetMapping("/{id}")
	public ResponseEntity<?> getProfile(@PathVariable Long id){
		Optional<ProfileDto> profileDto = profileService.findById(id);
		if(profileDto.isPresent()){
			return ResponseEntity.ok(profileDto.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}*/

	@GetMapping("/{username}")
	public ResponseEntity<?> getProfileByUsername(@PathVariable String username){
		try {
			Profile profile = profileService.getProfileByUsername(username);

			if(profile != null){
				return ResponseEntity.ok(DtoMapperProfile.builder().setProfile(profile).build());
			}else {
				return ResponseEntity.notFound().build();
			}
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, no se encontro el perfil" + e.getMessage());
		}
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

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Profile profileDto, BindingResult result, @PathVariable Long id, Authentication authentication){
		if(result.hasErrors()){
			return validationService.handleValidationErrors(result);
		}
		if(!profileService.hasProfileOwner(authentication.getName(), id)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tienes permisos para editar este perfil");
		}

		Optional<ProfileDto> optionalProfileDto = profileService.update(profileDto, id);

		if(optionalProfileDto.isPresent()){
			return ResponseEntity.status(HttpStatus.CREATED).body(optionalProfileDto.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id, Authentication authentication){
		if(!profileService.hasProfileOwner(authentication.getName(), id)){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes los permisos para borrar este perfil");
		}

		Optional<ProfileDto> profileDto = profileService.findById(id);

		if(profileDto.isPresent()){
			profileService.remove(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}
