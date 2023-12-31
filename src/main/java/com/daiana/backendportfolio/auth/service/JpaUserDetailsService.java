package com.daiana.backendportfolio.auth.service;

import com.daiana.backendportfolio.models.entities.Person;
import com.daiana.backendportfolio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	@Autowired
	private PersonRepository personRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> optionalPerson = personRepository.findByUsername(username);
		if(optionalPerson.isEmpty()){
			throw new UsernameNotFoundException(String.format("Username % no existe", username));
		}
		Person person = optionalPerson.orElseThrow();

		List<GrantedAuthority> authorities = person.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		return new User(person.getUsername(),
				person.getPassword(),
				true,
				true,
				true,
				true,
				authorities);
	}
}
