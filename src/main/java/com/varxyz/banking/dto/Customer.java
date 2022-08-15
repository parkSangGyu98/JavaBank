package com.varxyz.banking.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Customer {
	private String id;
	private String passwd;
	private String name;
	private String ssn;
	private String phone;

	public Customer() {
		
	}
	
	public Customer(String id) {
		this.id = id;
	}
	
}
