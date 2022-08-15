package com.varxyz.banking.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.varxyz.banking.dao.AccountDao;
import com.varxyz.banking.data.DataSourceConfig;
import com.varxyz.banking.dto.Account;
import com.varxyz.banking.service.AccountService;
import com.varxyz.banking.service.AccountServiceImpl;

@Controller("controller.checkAccountController")
public class CheckAccountController {
	
	AccountService accountService = new AccountServiceImpl();
	
	@GetMapping("/controller/check_account")
	public String checkAccountForm() {
		return "/account/check_account";
	}
	
	@PostMapping("/controller/check_account")
	public String checkAccountById(Account account, Model model) {
		
		List<Account> accountArr = accountService.checkAccountById(account.getCustomerId());
		model.addAttribute("accountArr", accountArr);
		AccountService.context.close();
		
		return "/account/success_check_account";
	}
	
}
