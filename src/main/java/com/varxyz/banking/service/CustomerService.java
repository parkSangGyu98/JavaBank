package com.varxyz.banking.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.banking.dao.CustomerDao;
import com.varxyz.banking.data.DataSourceConfig;
import com.varxyz.banking.dto.Customer;

public interface CustomerService {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	CustomerDao dao = context.getBean("customerDao", CustomerDao.class);
	
	public void addCustomer(Customer customer);
	
	Customer checkAccountPasswd(String accountNum);
	
	Customer login(String customerId);
	
}
