package com.varxyz.banking.dao;

import static java.sql.Types.CHAR;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.banking.dto.Account;
import com.varxyz.banking.dto.CheckingAccount;
import com.varxyz.banking.dto.Customer;
import com.varxyz.banking.dto.SavingsAccount;
import com.varxyz.banking.rowMapper.CustomerAccountRowMapper;


public class AccountDao {
	private JdbcTemplate jdbcTemplate;
	
	public AccountDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//계좌 생성
	public void addAccount(Account account) {
		String sql = "INSERT INTO Account(customerId, accountNum, accType) VALUES (?, ?, ?)";
        
		Object[] args = null;
		int[] types = new int[] {CHAR, CHAR, CHAR};
		
		if(account instanceof SavingsAccount) {
			SavingsAccount sa = (SavingsAccount)account;
			args = new Object[] {
					sa.getCustomerId(), sa.getAccountNum(), sa.getAccType()};
		}else {
			CheckingAccount ca = (CheckingAccount)account;
			args = new Object[] {
					ca.getCustomerId(), ca.getAccountNum(), ca.getAccType()};
		}
		jdbcTemplate.update(sql, args, types);
	}
	
	//ID로 계좌 정보 조회
	public List<Account> checkAccountById(String customerId) {
		String sql = "SELECT a.customerId, a.accountNum, a.accType,"
					+ " a.balance, a.interestRate, a.overAmount, a.regDate "
					+ "FROM Account a INNER JOIN "
					+ "Customer c ON a.customerId = c.id "
					+ "WHERE a.customerId = ?";
		return jdbcTemplate.query(sql, new CustomerAccountRowMapper(), customerId);	
	}
	
	//계좌번호로 계좌 정보 조회
	public List<Account> checkAccountByAccountNum(String accountNum) {
		String sql = "SELECT a.customerId, a.accountNum, a.accType,"
					+ " a.balance, a.interestRate, a.overAmount, a.regDate "
					+ "FROM Account a INNER JOIN "
					+ "Customer c ON a.customerId = c.id "
					+ "WHERE a.accountNum = ?";
		try {
			return jdbcTemplate.query(sql, new CustomerAccountRowMapper(), accountNum);	
		} catch (EmptyResultDataAccessException e) {
			Account account = new Account();
			return (List<Account>) account;
		}
	}
	
	//계좌번호로 잔액 조회
	public List<Account> getBalance(String accountNum) {
		String sql = "SELECT balance, customerId FROM Account WHERE accountNum = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class), accountNum);
	}
	
	//계좌번호로 잔액 확인 (for 이체기능)
	public Account checkingBalance(String accountNum) {
		String sql = "SELECT balance FROM Account WHERE accountNum = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), accountNum);
	}
	
	//입금
	public void deposit(String accountNum, double money) { 
		String sql = "UPDATE Account SET balance = ? WHERE accountNum = ?";
		jdbcTemplate.update(sql, checkingBalance(accountNum).getBalance()+money, accountNum );
	}
	
	//출금
	public void withdraw(String accountNum, double money) {
		String sql = "UPDATE Account SET balance = ? WHERE accountNum = ?";
		jdbcTemplate.update(sql, checkingBalance(accountNum).getBalance()-money, accountNum);
	}
	
	
	
}
















