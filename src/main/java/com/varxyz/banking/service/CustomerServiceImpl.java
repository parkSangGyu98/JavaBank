package com.varxyz.banking.service;

import com.varxyz.banking.dto.Customer;

public class CustomerServiceImpl implements CustomerService{
	
	@Override
	public void addCustomer(Customer customer) {
		dao.addCustomer(customer);
	}
	
	@Override
	public Customer checkAccountPasswd(String accountNum) {
		return dao.checkAccountPasswd(accountNum);
	}

	@Override
	public Customer login(String customerId) {
		return dao.login(customerId);
	}
}
