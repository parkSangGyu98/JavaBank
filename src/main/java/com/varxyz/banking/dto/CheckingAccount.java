package com.varxyz.banking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingAccount extends Account{
	private double overAmount;
}
