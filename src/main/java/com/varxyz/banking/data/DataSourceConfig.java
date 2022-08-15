package com.varxyz.banking.data;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.banking.dao.AccountDao;
import com.varxyz.banking.dao.CustomerDao;

@Configuration 
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/banking?serverTimezone=Asia/Seoul");
		ds.setUsername("banking");
		ds.setPassword("banking");
		ds.setInitialSize(2); 
		ds.setMaxActive(10);  
		ds.setMaxIdle(10); 	  
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public CustomerDao customerDao() {
		return new CustomerDao(dataSource());
	}
	
	@Bean
	public AccountDao accountDao() {
		return new AccountDao(dataSource());
	}
	
	
	
}
