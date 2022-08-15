package com.varxyz.banking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.banking.dto.Account;
import com.varxyz.banking.dto.CheckingAccount;
import com.varxyz.banking.dto.SavingsAccount;
import com.varxyz.banking.service.AccountService;
import com.varxyz.banking.service.AccountServiceImpl;

@Controller("controller.addAccountController")
public class AddAccountController {

	AccountService accountService = new AccountServiceImpl();

	@GetMapping("/controller/add_account")
	public String addAccountForm(HttpSession session) {
		if( session.getAttribute("customerId") == null ) {
			return "customer/login";
		}
		return "account/add_account";
	}

	@PostMapping("/controller/add_account")
	public String addAccount(Account account, HttpSession session, Model model) {

		String numStr = String.valueOf((int) (Math.random() * 1000000000));
		StringBuilder sb = new StringBuilder();
		sb.append(numStr.substring(0, 3));
		sb.append("-");
		sb.append(numStr.substring(3, 5));
		sb.append("-");
		sb.append(numStr.substring(5));
		
		if( account.getAccType() != null) {
			Account newAccount = null;
			if (account.getAccType().equals("S")) {
				newAccount = new SavingsAccount();
			} else {
				newAccount = new CheckingAccount();
			}
			newAccount.setAccountNum(sb.toString());
			newAccount.setCustomerId((String)session.getAttribute("customerId"));
			newAccount.setAccType(account.getAccType());

			accountService.addAccount(newAccount);
			AccountService.context.close();

			return "redirect:/controller/main_page";
		}else {
			model.addAttribute("msg", "생성할 계좌를 선택해 주세요.");
			return "error/alert";
		}
		
	}
	
	

}
