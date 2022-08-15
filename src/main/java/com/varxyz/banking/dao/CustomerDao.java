package com.varxyz.banking.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.banking.dto.Customer;

public class CustomerDao {
	private JdbcTemplate jdbcTemplate;

	public CustomerDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 회원가입
	public void addCustomer(Customer customer) {
		String sql = "INSERT INTO Customer (id, passwd, name, ssn, phone) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, customer.getId(), customer.getPasswd(), customer.getName(), customer.getSsn(),
				customer.getPhone());
	}

	// 계좌번호로 비밀번호 일치여부 확인
	public Customer checkAccountPasswd(String accountNum) {
		String sql = "SELECT C.passwd FROM Customer C INNER JOIN "
				+ "Account A ON C.id = A.customerId WHERE A.accountNum = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), accountNum);
	}

	// 로그인
	public Customer login(String customerId) {
		String sql = "SELECT id, passwd FROM Customer WHERE id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), customerId);
		} catch (EmptyResultDataAccessException e) {
			Customer result = new Customer();
			result.setId("실패다이 자식아");
			return result;
		}
	}

}
