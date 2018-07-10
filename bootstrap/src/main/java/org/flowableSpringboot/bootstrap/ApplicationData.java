package org.flowableSpringboot.bootstrap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="loan_application")
public class ApplicationData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private final String fullName;
	private final String email;
	private final String phoneNumber;

	public ApplicationData(String name, String email, String phoneNumber) {
		this.fullName = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phoneNumber;
	}
}
