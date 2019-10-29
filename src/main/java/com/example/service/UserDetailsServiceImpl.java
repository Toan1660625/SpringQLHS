package com.example.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.entity.User;
import com.example.repository.UserRepository;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  UserDetailsServiceImpl class
 * @version 1.0
 * @author ToanLM
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	    private WebApplicationContext applicationContext;
	
	@Autowired
	private UserRepository userRepository;

	public UserDetailsServiceImpl() {
		super();
	}
	
	@PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(UserRepository.class);
    }

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		String role = user.getRole();
		grantedAuthorities.add(new SimpleGrantedAuthority(role));
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		System.out.println(user.getUserName() + user.getPassword() + grantedAuthorities );
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}

}
