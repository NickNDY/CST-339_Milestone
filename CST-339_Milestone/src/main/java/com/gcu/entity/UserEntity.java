package com.gcu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity representing a user layer
 */
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipcode;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username;}

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname;}

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname;}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone;}

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address;}

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city;}

    public String getState() { return state; }
    public void setState(String state) { this.state = state;}

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode;}
}
