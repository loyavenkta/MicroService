package com.vz.customerservicereactive.model;

import javax.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Customer {
	@Id
	@Column(name= "EMAIL_ID")
	 private String email;
	@Column(name= "first_name")
	 private String firstname;
	@Column(name= "last_name")
	 private String lastname;

}
