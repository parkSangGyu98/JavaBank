package com.varxyz.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.banking.dto.Customer;
import com.varxyz.banking.service.CustomerService;
import com.varxyz.banking.service.CustomerServiceImpl;

@Controller("controller.addCustomerController")
public class AddCustomerController {

	CustomerService customerService = new CustomerServiceImpl();

	@GetMapping("/controller/add_customer")
	public String addCustomerForm() {
		return "/customer/add_customer";
	}

	@PostMapping("/controller/add_customer")
	public String addCustomer(Customer customer, Model model) {
		if (customer.getName() == "" || customer.getId() == "" || customer.getPasswd() == "" || customer.getSsn() == ""
				|| customer.getPhone() == "") {
			model.addAttribute("msg", "빈칸을 입력해 주세요.");
			return "error/alert";
		}
		if( customerService.login(customer.getId()).getId().equals(customer.getId())) {
			model.addAttribute("msg", "이미 사용중인 ID 입니다.");
			return "error/alert";
		}
		customerService.addCustomer(customer);
		model.addAttribute("customer", customer);
		CustomerService.context.close();
		return "customer/login";

	}

}
