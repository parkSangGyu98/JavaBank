package com.varxyz.banking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.banking.dto.Customer;
import com.varxyz.banking.service.CustomerService;
import com.varxyz.banking.service.CustomerServiceImpl;

@Controller
public class LoginController {

	CustomerService customerService = new CustomerServiceImpl();

	@GetMapping("/controller/login")
	public String loginForm(HttpSession session) {
		session.invalidate(); //기존 세션값 모두 삭제
		return "customer/login";
	}

	@PostMapping("/controller/login")
	public String addAccount(Customer customer, HttpSession session,  HttpServletRequest request, Model model) {
		session = request.getSession();
		if (customerService.login(customer.getId()).getId().equals(customer.getId())
		 && customerService.login(customer.getId()).getPasswd().equals(customer.getPasswd())) {
			customerService.context.close();
			session.setAttribute("customerId", customer.getId());
			return "redirect:/controller/main_page";
		}
		model.addAttribute("msg", "올바른 아이디 또는 비밀번호를 입력해주세요.");
		return "error/alert";
	}

}
