package com.formation.msglc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Identifiant {
	@Email
	@NotEmpty
	@Pattern(regexp = ".+@.+\\..+", message = "Mauvise adresse Email")
	private String email;
	@NotEmpty
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
