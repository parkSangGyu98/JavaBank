package com.varxyz.banking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.varxyz.banking.service.AccountService;
import com.varxyz.banking.service.AccountServiceImpl;
import com.varxyz.banking.service.CustomerService;
import com.varxyz.banking.service.CustomerServiceImpl;

@Controller("controller.getDetailController")
public class GetDetailController {
AccountService accountService = new AccountServiceImpl();
CustomerService customerService = new CustomerServiceImpl();
	
	@GetMapping("/controller/get_Detail")
	public String getDetailForm(HttpSession session, Model model) {
		if( session.getAttribute("customerId") == null ) {
			return "customer/login";
		}
		model.addAttribute("accountNum", accountService.checkAccountById((String)session.getAttribute("customerId")));
		return "account/get_Detail";
	}
	
//	@PostMapping("/controller/get_Detail")
	public String checkAccountById(Model model, @RequestParam String index) {
		System.out.println(index);
		return "/account/get_Detail";
	}
	
}
