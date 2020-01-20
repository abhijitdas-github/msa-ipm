package com.ipm.microservices.salesorderservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="CUSTOMER_SOS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerSOS {

	@Id
	@Column(name="CUST_ID")
	private Long id;
	
	@Column(name="CUST_FIRST_NAME")
	private String firstName;
	
	@Column(name="CUST_LAST_NAME")
	private String lastName;
	
	@Column(name="CUST_EMAIL")
	private String email;
	
	public CustomerSOS(Long id, String firstname, String lastname, String emailid) {
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = emailid;
	}
	
	public CustomerSOS() {
	
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
