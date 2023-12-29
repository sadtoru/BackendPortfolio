package com.daiana.backendportfolio.repositories;

import com.daiana.backendportfolio.models.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
