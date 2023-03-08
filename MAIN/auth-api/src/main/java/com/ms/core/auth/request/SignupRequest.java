package com.ms.core.auth.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequest {
	@NotBlank
	@Size(min = 4, max = 40)
	private String firstName;

	@NotBlank
	@Size(min = 4, max = 40)
	private String lastName;

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String avatar;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 10)
	private String role;
}
