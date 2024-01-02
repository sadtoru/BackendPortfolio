package com.daiana.backendportfolio.controllers;

import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping
	public List<ProfileDto> getAllProfiles(){
		return profileService.findAll();
	}
}
