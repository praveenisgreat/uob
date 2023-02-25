package com.ms.core.auth.repository.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.core.auth.model.AbilityModel;
import com.ms.core.auth.model.UserModel;

import lombok.Data;

@Data
public class UserPrincipal implements UserDetails{

	private static final long serialVersionUID = -6248099593834877628L;
	
	private Long id;

	private String fullName;

	private String username;
	
	@JsonIgnore
	private String password;

	private String avatar;
	
	private String email;

	private String role;
	
	private Collection<? extends AbilityModel> ability;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(Long id, String fullName, String username, String password, String avatar, String email, String role,
						 Collection<? extends AbilityModel> ability) {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.email = email;
		this.role = role;
		this.ability = ability;
	}
	
	public static UserPrincipal create(UserModel userModel) {
		return new UserPrincipal(
						userModel.getId(), 
						userModel.getFirstName()+" "+userModel.getLastName(),
						userModel.getUsername(),
						userModel.getPassword(), 
						userModel.getAvatar(), 
						userModel.getEmail(), 
						userModel.getRole(),
						userModel.getAbility()
				);
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
