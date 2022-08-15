package com.varxyz.banking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.banking.service.AccountService;
import com.varxyz.banking.service.AccountServiceImpl;
import com.varxyz.banking.service.CustomerService;
import com.varxyz.banking.service.CustomerServiceImpl;

@Controller
public class WithdrawController {
	
	AccountService accountService = new AccountServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
		
	@GetMapping("/controller/withdraw")
	public String withdrawForm(HttpSession session, Model model) {
		if( session.getAttribute("customerId") == null ) {
			return "customer/login";
		}
		model.addAttribute("accountNum", accountService.checkAccountById((String)session.getAttribute("customerId")));
		return "account/withdraw";
	}
	
	@PostMapping("/controller/withdraw")
	public String deposit(String sendAccountNum, String passwd, String money, Model model) {
		if (sendAccountNum == "" || passwd == "" || money.isEmpty() == true) {
			model.addAttribute("msg", "빈칸을 입력해 주세요.");
			return "error/alert";
		}
		if( customerService.checkAccountPasswd(sendAccountNum).getPasswd().equals(passwd) ) {
			Double dMoney = Double.valueOf(money);
			if(dMoney > 0) {
				if(accountService.checkingBalance(sendAccountNum).getBalance() >= dMoney) {
					accountService.withdraw(sendAccountNum, dMoney);
					model.addAttribute("sendAccountNum", sendAccountNum);
					model.addAttribute("money", dMoney);
					return "redirect:/controller/main_page";
				}else {
					model.addAttribute("msg", "잔고부족");
					return "error/alert";
				}
			}else {
				model.addAttribute("msg", "올바른 금액을 입력해 주세요.");
				return "error/alert";
			}
		}else {
			model.addAttribute("msg", "올바른 비밀번호를 입력해주세요.");
			return "error/alert";
		}
		
	}
}
