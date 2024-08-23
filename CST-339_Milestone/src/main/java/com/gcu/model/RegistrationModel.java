package com.gcu.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class RegistrationModel {

	@NotNull(message="First name is a required field")
	@Size(min=1, max=32, message="First name must be between 1 and 32 characters")
	private String firstname;
	
	@NotNull(message="Last name is a required field")
	@Size(min=1, max=32, message="Last name must be between 1 and 32 characters")
	private String lastname;
	
	@NotNull(message="Email is a required field")
	@Size(min=3, max=32, message="Email must be between 3 and 32 characters")
	@Email(message="Must be a properly formatted email")
	private String email;
	
	@NotNull(message="Phone number is a required field")
	@Size(min=7, max=14, message="Phone number must be between 7 and 14 characters")
	@Digits(integer=14, fraction = 0, message="Phone number must consist of no more than 14 digits")
	private String phone;
	
	@NotNull(message="User name is a required field")
	@Size(min=3, max=32, message="User name must be between 3 and 32 characters")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=3, max=32, message="Password must be between 3 and 32 characters")
	private String password;
	
	@NotNull(message="Address is a required field")
	@Size(min=4, max=128, message="Address must be between 4 and 128 characters")
	private String address;

	@NotNull(message="City is a required field")
	@Size(min=2, max=32, message="City must be between 2 and 32 characters")
	private String city;

	@NotNull(message="State is a required field")
	@Size(min=2, max=32, message="State must be between 2 and 32 characters")
	private String state;

	@NotNull(message="Zip code is a required field")
	@Size(min=5, max=14, message="Zip code must be between 5 and 14 characters")
	@Digits(integer=14, fraction = 0, message="Zip code must consist of no more than 14 digits")
	private String zipcode;

	public RegistrationModel(
			@NotNull(message = "First name is a required field") @Size(min = 1, max = 32, message = "First name must be between 1 and 32 characters") String firstname,
			@NotNull(message = "Last name is a required field") @Size(min = 1, max = 32, message = "Last name must be between 1 and 32 characters") String lastname,
			@NotNull(message = "Email is a required field") @Size(min = 3, max = 32, message = "Email must be between 3 and 32 characters") @Email String email,
			@NotNull(message = "Phone number is a required field") @Size(min = 7, max = 14, message = "Phone number must be between 7 and 14 characters") @Digits(integer = 14, fraction = 0, message = "Phone number must consist of no more than 14 digits") String phone,
			@NotNull(message = "User name is a required field") @Size(min = 3, max = 32, message = "User name must be between 3 and 32 characters") String username,
			@NotNull(message = "Password is a required field") @Size(min = 3, max = 32, message = "Password must be between 3 and 32 characters") String password,
			@NotNull(message = "Address is a required field") @Size(min = 4, max = 128, message = "Address must be between 4 and 128 characters") String address,
			@NotNull(message = "City is a required field") @Size(min = 2, max = 32, message = "City must be between 2 and 32 characters") String city,
			@NotNull(message = "State is a required field") @Size(min = 2, max = 32, message = "State must be between 2 and 32 characters") String state,
			@NotNull(message = "Zip code is a required field") @Size(min = 5, max = 14, message = "Zip code must be between 5 and 14 characters") @Digits(integer = 14, fraction = 0, message = "Zip code must consist of no more than 14 digits") String zipcode) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public RegistrationModel() {
		this.firstname = "";
		this.lastname = "";
		this.email = "";
		this.phone = "";
		this.username = "";
		this.password = "";
		this.address = "";
		this.city = "";
		this.state = "";
		this.zipcode = "";
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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
