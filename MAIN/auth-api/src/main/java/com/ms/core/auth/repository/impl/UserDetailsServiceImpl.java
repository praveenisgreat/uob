package com.ms.core.auth.repository.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.core.auth.model.UserModel;
import com.ms.core.auth.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// User can login with either username or email
        UserModel user = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username : " + username));
        System.out.println("loadUserByUsername="+username);
		return UserPrincipal.create(user);
	}

	// This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
    	UserModel user = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));

        return UserPrincipal.create(user);
    }
}
