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
public class TransferController {

	AccountService accountService = new AccountServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();

	@GetMapping("/controller/transfer")
	public String transferForm(HttpSession session, Model model) {
		if (session.getAttribute("customerId") == null) {
			return "customer/login";
		}
		model.addAttribute("accountNum", accountService.checkAccountById((String) session.getAttribute("customerId")));
		return "account/transfer";
	}

	@PostMapping("/controller/transfer")
	public String transfer(String sendAccountNum, String getAccountNum, String passwd, String money, Model model) {
		if (sendAccountNum == "" || getAccountNum == "" || passwd == "" || money.isEmpty() == true) {
			model.addAttribute("msg", "빈칸을 입력해 주세요.");
			return "error/alert";
		}
		if (customerService.checkAccountPasswd(sendAccountNum).getPasswd().equals(passwd)) {
			Double dMoney = Double.valueOf(money);
			if (dMoney > 0) {
				if (accountService.checkingBalance(sendAccountNum).getBalance() >= dMoney) {
					if (accountService.checkAccountByAccountNum(getAccountNum) != null) {
						if (!sendAccountNum.equals(getAccountNum)) {
							accountService.withdraw(sendAccountNum, dMoney);
							accountService.deposit(getAccountNum, dMoney);
							return "redirect:/controller/main_page";
						} else {
							model.addAttribute("msg", "본인 계좌로의 이체는 불가능 합니다.");
							return "error/alert";
						}
					} else {
						model.addAttribute("msg", "받으시는 분의 계좌가 존재하지 않습니다.");
						return "error/alert";
					}
				} else {
					model.addAttribute("msg", "잔고부족");
					return "error/alert";
				}
			} else {
				model.addAttribute("msg", "올바른 금액을 입력해 주세요.");
				return "error/alert";
			}
		} else {
			model.addAttribute("msg", "올바른 비밀번호를 입력해주세요.");
			return "error/alert";
		}
	}
}
