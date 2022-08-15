package com.varxyz.banking.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account {
	private Customer customer;
	private String customerId;
	private String accountNum;
	private String accType;
	private double balance;
	private double interestRate;
	private double overAmount;
	private String regDate;
}

