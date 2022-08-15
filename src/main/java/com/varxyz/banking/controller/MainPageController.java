package com.varxyz.banking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.varxyz.banking.service.AccountService;
import com.varxyz.banking.service.AccountServiceImpl;
import com.varxyz.banking.service.CustomerService;
import com.varxyz.banking.service.CustomerServiceImpl;

@Controller
public class MainPageController {
	
	AccountService accountService = new AccountServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	
	@GetMapping("/controller/main_page")
	public String mainPageForm(HttpSession session, Model model) {
		if( session.getAttribute("customerId") == null ) {
			return "customer/login";
		}
		model.addAttribute("accountNum", accountService.checkAccountById((String)session.getAttribute("customerId")));
		return "main_page/main_page";
	}
	
	
}
