package com.varxyz.banking.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.varxyz.banking.dto.Account;
import com.varxyz.banking.dto.CheckingAccount;
import com.varxyz.banking.dto.SavingsAccount;

public class CustomerAccountRowMapper implements RowMapper<Account> {

	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = null;
		if(rs.getString("accountNum").length() != 0) {
			
			String accType = rs.getString("accType");
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			
			if(accType.equals("C")) {
				account = new CheckingAccount();
				CheckingAccount ca = (CheckingAccount)account;
				ca.setOverAmount(rs.getDouble("overAmount"));
			}else {
				account = new SavingsAccount();
				SavingsAccount sa = (SavingsAccount)account;
				sa.setInterestRate(rs.getDouble("interestRate"));
			}
			account.setCustomerId(rs.getString("customerId"));
			account.setAccountNum(rs.getString("accountNum"));
			account.setAccType(accType);
			account.setBalance(rs.getDouble("balance"));
			account.setInterestRate(rs.getDouble("interestRate"));
			account.setOverAmount(rs.getDouble("overAmount"));
			account.setRegDate(fmt.format(rs.getDate("regDate")));
			return account;
		}else {
			return account;
		}
		
	}

}
