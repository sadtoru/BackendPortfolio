package com.daiana.backendportfolio.models.dto.mapper;

import com.daiana.backendportfolio.models.dto.ProfileDto;
import com.daiana.backendportfolio.models.entities.Profile;

public class DtoMapperProfile {
	private Profile profile;

	private DtoMapperProfile(){
	}

	public static DtoMapperProfile builder(){
		return new DtoMapperProfile();
	}

	public DtoMapperProfile setProfile(Profile profile){
		this.profile = profile;
		return this;
	}

	public ProfileDto build(){
		if(profile == null){
			throw new RuntimeException("No se encontro profile");
		}
		return new ProfileDto(this.profile.getId(), this.profile.getName(), this.profile.getLastname(),
				this.profile.getAbout(), this.profile.getProfilePicture());
	}

}
