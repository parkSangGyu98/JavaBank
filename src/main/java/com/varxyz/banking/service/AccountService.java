package com.varxyz.banking.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.banking.dao.AccountDao;
import com.varxyz.banking.data.DataSourceConfig;
import com.varxyz.banking.dto.Account;

public interface AccountService {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	AccountDao dao = context.getBean("accountDao", AccountDao.class);
	
	void addAccount(Account account);
	
	List<Account> checkAccountById(String customerId);
	
	List<Account> getBalance(String accountNum);
	
	void deposit(String accountNum, double money);
	
	void withdraw(String accountNum, double money);
	
	Account checkingBalance(String accountNum);
	
	List<Account> checkAccountByAccountNum(String accountNum);
}
