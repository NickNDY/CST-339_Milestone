package com.gcu;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationModel {

	@NotNull(message="First name is a required field")
	@Size(min=1, max=32, message="First name must be between 1 and 32 characters")
	private String firstname;
	
	@NotNull(message="Last name is a required field")
	@Size(min=1, max=32, message="Last name must be between 1 and 32 characters")
	private String lastname;
	
	@NotNull(message="Email is a required field")
	@Size(min=3, max=32, message="Email must be between 3 and 32 characters")
	@Email
	private String email;
	
	@NotNull(message="Phone number is a required field")
	@Size(min=7, max=14, message="Phone number must be between 7 and 14 characters")
	@Digits(integer=14, fraction = 0, message="Phone number must consist of no more than 14 digits")
	private String phone;
	
	@NotNull(message="User name is a required field")
	@Size(min=3, max=32, message="User name must be between 3 and 32 characters")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=3, max=32, message="User name must be between 3 and 32 characters")
	private String password;
	
	public RegistrationModel(String firstname, String lastname, String email, String phone, String username, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
	public RegistrationModel() {
		this.firstname = "John";
		this.lastname = "Doe";
		this.email = "none@mail.com";
		this.phone = "1234567890";
		this.username = "none";
		this.password = "none";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
