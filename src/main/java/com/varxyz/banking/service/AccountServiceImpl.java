package com.varxyz.banking.service;

import java.util.List;

import com.varxyz.banking.dto.Account;

public class AccountServiceImpl implements AccountService{
	
	@Override
	public void addAccount(Account account) {
		dao.addAccount(account);
	}

	@Override
	public List<Account> checkAccountById(String customerId) {
		return dao.checkAccountById(customerId);
	}

	@Override
	public List<Account> getBalance(String accountNum) {
		return dao.getBalance(accountNum);
	}

	@Override
	public void deposit(String accountNum, double money) {
		dao.deposit(accountNum, money);
	}

	@Override
	public void withdraw(String accountNum, double money) {
		dao.withdraw(accountNum, money);
	}

	@Override
	public Account checkingBalance(String accountNum) {
		return dao.checkingBalance(accountNum);
	}

	@Override
	public List<Account> checkAccountByAccountNum(String accountNum) {
		return dao.checkAccountByAccountNum(accountNum);
	}
	
}
