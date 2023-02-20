package com.ms.core.auth.repository.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.core.auth.model.UserModel;

import lombok.Data;

@Data
public class UserPrincipalImpl implements UserDetails{

	private static final long serialVersionUID = -6248099593834877628L;

	private Long id;

	private String firstName;

	private String username;

	private String lastName;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipalImpl(Long id, String firstName, String lastName, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserPrincipalImpl create(UserModel userModel) {
		
		List<GrantedAuthority> authorities = userModel.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserPrincipalImpl(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getUsername(), userModel.getEmail(), userModel.getPassword(),
				authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
