package com.varxyz.banking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.banking.dto.Account;
import com.varxyz.banking.service.AccountService;
import com.varxyz.banking.service.AccountServiceImpl;

@Controller
public class GetBalanceController {
	
	AccountService accountService = new AccountServiceImpl();
	
	@GetMapping("/controller/get_balance")
	public String getBalanceForm() {
		return "account/get_balance";
	}
	
	@PostMapping("/controller/get_balance")
	public String getBalance(String accountNum, Model model) {
		List<Account> account = accountService.getBalance(accountNum);
		model.addAttribute("account", account);
		AccountService.context.close();
		return "account/success_get_balance";
	}
	
}
