# JavaBank ๐ฆ
+ ๊ธฐ๋ณธ์ ์ธ ์ํ ์๋น์ค๋ฅผ ๊ตฌํํ ๊ฐ์ธํ๋ก์ ํธ์๋๋ค.
+ 2022.7.25 ~ 2022.7.31
+ ์ฒซ ๊ฐ์ธํ๋ก์ ํธ๋ผ ๋ง์ด ๋ถ์กฑํ ์  ์ดํดํด์ฃผ์๊ธธ ๋ฐ๋๋๋ค ๐ฅ
## ์ฌ์ฉํ ๊ธฐ์  ๋ฐ ํ๊ฒฝ
+ Window
+ eclipse
+ Java
+ Spring
+ Jsp
+ MySQL
+ HTML/CSS3
+ Javascript
+ bootstrap

## ๊ตฌํ ๊ธฐ๋ฅ
+ ๊ณ์ข 
  + ๋๋คํ ๊ณ์ข๋ฒํธ์ธ ์ ๊ธ ๋๋ ๋์ถ ๊ณ์ข ์์ฑ
  + ๊ณ์ข ๋ด์ญ ์กฐํ ๋ฐ ๊ณ์ข ์์ธ๋ด์ญ ์กฐํ
+ ์ฌ์ฉ์
  + ํ์๊ฐ์, ๋ก๊ทธ์ธ, ๋ก๊ทธ์์
  + ๊ณ์ข์ด์ฒด
  + ์๊ธ, ์ถ๊ธ
  
 ## ์ฃผ์ ์ฝ๋
 ### ๊ณ์ข
 + ๋๋ค ๊ณ์ข๋ฒํธ ์์ฑ
   1. ๋๋คํจ์๋ฅผ ์ด์ฉํ์ฌ "123-45-6789" ํํ์ ์ซ์๋ฅผ ๊ฐ์ ธ์ต๋๋ค.
   2. ๊ณ์ข๋ฅผ ์์ฑํ  ๋ SavingAccount ์ ํ ์ "S"๋ฅผ, CheckingAccount ์ ํ ์ "C"๋ฅผ ๋ฐ์์ ์๋ง๋ ๊ณ์ข๋ฅผ ์์ฑํด์ฃผ๋ฉฐ DB์ ์ ์ฅ๋ค.
 
           AddAccountController ์ผ๋ถ

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
             model.addAttribute("msg", "์์ฑํ  ๊ณ์ข๋ฅผ ์ ํํด ์ฃผ์ธ์.");
             return "error/alert";
            }

           }

+ ๊ณ์ข ์์ธ๋ด์ญ ์กฐํ
  1. ๋ฒํผ์ ํด๋ฆญํ๊ธฐ์ ์ jsp ์์ ์ปจํ์ธ  ๋ถ๋ถ๋ง ๋ฐ๊ฟ์ ์์ํ์์ต๋๋ค.
  2. ๋ก๊ทธ์ธ ์ ๋ฑ๋กํ session๊ฐ(๋ก๊ทธ์ธํ ID) ๋ฅผ ์ด์ฉํ์ฌ DB์์ ๋ณด์ ์ค์ธ ๊ณ์ข์ ๋ณด๋ฅผ ๊ฐ์ ธ์ต๋๋ค.
  3. ๊ฐ์ ธ์จ ๊ณ์ข์ ๋ณด๋ค์ for๋ฌธ์ ์ด์ฉํ์ฌ ์์ธ์ ๋ณด๋ฅผ ์กฐํ์ํต๋๋ค.
  4. ๋์๊ฐ๊ธฐ ๋ฒํผ ํด๋ฆญ์ main_page ์ปจํธ๋กค๋ฌ๋ก ์ด๋ํฉ๋๋ค.

           get_Detail.jsp ์ผ๋ถ

           <form action="main_page" method="get" style="text-align: center; margin-bottom: 50px;">
			       <button class="card-text mb-auto getBalanceButton"
              type="submit">๋์๊ฐ๊ธฐ</button>
            </form>
            <div class="row mb-2">

             <c:forEach var="account" items="${accountNum}">
              <div class="col-md-6">
               <div
                class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                 <c:if test="${account.accType eq 'S'}">
                  <h3 class="mb-0">Saving Account</h3>
                 </c:if>
                 <c:if test="${account.accType eq 'C'}">
                  <h3 class="mb-0">Checking Account</h3>
                 </c:if>
                 <p class="card-text mb-auto">${account.accountNum}</p>
                 <div class="mb-1">์๊ณ  : ${account.balance}์</div>
                 <div class="mb-1">์ด์์จ : ${account.interestRate}%</div>
                 <div class="mb-1">๋์ถํ๋ : ${account.overAmount}์</div>

                </div>
               </div>
              </div>
             </c:forEach>

            </div>
	
### ์ฌ์ฉ์
+ ๋ก๊ทธ์ธ
  1. ์๋ ฅํ ์์ด๋ ๋น๋ฐ๋ฒํธ์ DB์ ์ ์ฅ๋ ์์ด๋ ๋น๋ฐ๋ฒํธ์ ์ผ์น์ฌ๋ถ๋ฅผ ํ์ธํ ๋ค ๋ก๊ทธ์ธ ์ํต๋๋ค.
  2. ์ ํจ์ฑ๊ฒ์ฌ๋ฅผ ํต๊ณผํ ๊ฒฝ์ฐ ๋ก๊ทธ์ธํ ์ฌ์ฉ์์ ID๋ฅผ session์ ์ ์ฅ์ํต๋๋ค.
	
		    LoginController ์ผ๋ถ

		    @PostMapping("/controller/login")
			public String addAccount(Customer customer, HttpSession session,  HttpServletRequest request, Model model) {
			session = request.getSession();
			if (customerService.login(customer.getId()).getId().equals(customer.getId())
			 && customerService.login(customer.getId()).getPasswd().equals(customer.getPasswd())) {
				customerService.context.close();
				session.setAttribute("customerId", customer.getId());
				return "redirect:/controller/main_page";
			}
			model.addAttribute("msg", "์ฌ๋ฐ๋ฅธ ์์ด๋ ๋๋ ๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์.");
			return "error/alert";
			}
    
    3. ์คํจ ์ ํ๋ฉด
    
    ![image](https://user-images.githubusercontent.com/103983349/184834152-bd2073e6-9961-41de-9da7-7a7466661dcb.png)

+ ํ์๊ฐ์
  1. ์๋ ฅํ ์ ๋ณด๋ค์ ๊ณต๋ฐฑ ์ฌ๋ถ๋ฅผ ํ์ธํฉ๋๋ค.
  2. DB๋ด ์ ์ฅ๋ ID์ ์๋ ฅํ ID์ ์ค๋ณต ์ฌ๋ถ๋ฅผ ํ์ธํฉ๋๋ค.
  3. ์ด์ ์์ ์, DB์ ์ ๋ณด๋ฅผ ์ ์ฅํจ๊ณผ ๋์์ ๋ก๊ทธ์ธ ํ์ด์ง๋ก ํ๋ฉด์ ์ ํํฉ๋๋ค.

			@PostMapping("/controller/add_customer")
			public String addCustomer(Customer customer, Model model) {
				if (customer.getName() == "" || customer.getId() == "" || customer.getPasswd() == "" || customer.getSsn() == ""
						|| customer.getPhone() == "") {
					model.addAttribute("msg", "๋น์นธ์ ์๋ ฅํด ์ฃผ์ธ์.");
					return "error/alert";
				}
				if( customerService.login(customer.getId()).getId().equals(customer.getId())) {
					model.addAttribute("msg", "์ด๋ฏธ ์ฌ์ฉ์ค์ธ ID ์๋๋ค.");
					return "error/alert";
				}
				customerService.addCustomer(customer);
				CustomerService.context.close();
				return "customer/login";
			}


+ ๊ณ์ข์ด์ฒด
  1. ๋ณธ์ธ ๊ณ์ข๋ฅผ ์๋ ฅํ๋ ๋ฒ๊ฑฐ๋ก์์ ๊ฐ์ํ์ฌ session์ ์ด์ฉํด ํ์ฌ ๋ก๊ทธ์ธ ํ ์ ์ ๊ฐ ๋ณด์ ํ ๊ณ์ข๋ฒํธ๋ค์ select ๋ฐ์ค๋ฅผ ์ด์ฉํด ๋ฏธ๋ฆฌ ๋ณด์ฌ์ฃผ๋ฉฐ ์ ํํ  ์ ์๋๋ก ํฉ๋๋ค.
  2. ๊ณ์ข์ ํ์ ์ํ  ๊ฒฝ์ฐ, ๋น๋ฐ๋ฒํธ ์ค๋ฅ, ๊ธ์ก์๋ ฅ ์ค๋ฅ, ๋์ผ ๊ณ์ข๋ก์ ์ด์ฒด๋ฅผ ํ  ๊ฒฝ์ฐ์ ์ ํจ์ฑ ๊ฒ์ฌ๋ฅผ ํ์์ต๋๋ค.
  3. ์ด์ ์์ ์ ๋ณด๋ด๋์ด์ ๊ณ์ข์ ์ถ๊ธ ๊ธฐ๋ฅ์, ๋ฐ๋์ด์ ๊ณ์ข์ ์๊ธ ๊ธฐ๋ฅ์ ๋ถ๋ฌ์์ต๋๋ค. (์๊ธ, ์ถ๊ธ ๊ตฌํํ ๋ ๋ง๋ค์ด ๋์๋ ๋ฉ์๋)
			
			transfer.jsp ์ผ๋ถ
			
			<form action="transfer" method="post">
			<h1 class="h3 mb-3 fw-normal" style="text-align:center;">๊ณ์ข์ด์ฒด</h1>

			<div class="form-floating">
				<select name="sendAccountNum" class="form-control" style="padding-top : 0.8rem;">
				    <option value="">๋ณด๋ด๋ ์ด ๊ณ์ข๋ฒํธ</option>
				    <c:forEach var="account" items="${accountNum}">
				    		<option value="${account.accountNum}">${account.accountNum}</option>
				    </c:forEach>
				</select>
			</div>
			<div class="form-floating">
				<input type="password" name="passwd" class="form-control"
					id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">๋น๋ฐ๋ฒํธ </label>
			</div>
			<div class="form-floating">
				<input type="text" name="getAccountNum"
					placeHolder="000-00-0000" class="form-control"
					id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">๋ฐ๋ ์ด ๊ณ์ข๋ฒํธ </label>
			</div>
			<div class="form-floating margin">
				<input type="number" name="money" class="form-control"
					id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">์ด์ฒด ๊ธ์ก </label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit">์ด์ฒด</button>
			</form>



			TransferController ์ผ๋ถ

			@PostMapping("/controller/transfer")
			public String transfer(String sendAccountNum, String getAccountNum, String passwd, String money, Model model) {
				if (sendAccountNum == "" || getAccountNum == "" || passwd == "" || money.isEmpty() == true) {
					model.addAttribute("msg", "๋น์นธ์ ์๋ ฅํด ์ฃผ์ธ์.");
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
									model.addAttribute("msg", "๋ณธ์ธ ๊ณ์ข๋ก์ ์ด์ฒด๋ ๋ถ๊ฐ๋ฅ ํฉ๋๋ค.");
									return "error/alert";
								}
							} else {
								model.addAttribute("msg", "๋ฐ์ผ์๋ ๋ถ์ ๊ณ์ข๊ฐ ์กด์ฌํ์ง ์์ต๋๋ค.");
								return "error/alert";
							}
						} else {
							model.addAttribute("msg", "์๊ณ ๋ถ์กฑ");
							return "error/alert";
						}
					} else {
						model.addAttribute("msg", "์ฌ๋ฐ๋ฅธ ๊ธ์ก์ ์๋ ฅํด ์ฃผ์ธ์.");
						return "error/alert";
					}
				} else {
					model.addAttribute("msg", "์ฌ๋ฐ๋ฅธ ๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์.");
					return "error/alert";
				}
			}
	    

 ## ๊ตฌํ ํ๋ฉด
 ### ๋ก๊ทธ์ธ
 + ๋ก๊ทธ์ธ ์คํจ ์ alert์ ์ด์ฉํด ๊ฒฝ๊ณ ๋ฌธ์ ๋์ฐ๊ณ  ๋ค์ ๋ก๊ทธ์ธ ํ๋ฉด์ผ๋ก ๋ณด๋๋๋ค.
 
 ![image](https://user-images.githubusercontent.com/103983349/184826589-0afcb32a-c4d8-43aa-b67f-0ff5a377ee79.png)

 ### ํ์๊ฐ์
 + ID์ค๋ณตํ์ธ์ ํ์๊ฐ์ ๋ฒํผ ํด๋ฆญ ์ ์๋ํฉ๋๋ค.
 
 ![image](https://user-images.githubusercontent.com/103983349/184826760-46824b75-cee6-4045-8255-10738db5c616.png)

 ### ๋ฉ์ธ
 + ๋ก๊ทธ์ธ ํ์๋ง์ ๋ณผ์์๋ ํ๋ฉด์ผ๋ก, ๋ณธ์ธ์ด ๋ณด์ ํ ๊ณ์ข๋ฅผ ๋ณด์ฌ์ค๋๋ค.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830508-fc254f54-6d00-46d7-8b95-c2cac0c72b1c.png)

 ### ์์ธ์กฐํ
 + ์ค๊ฐ ์์ธ์กฐํ ๋ฒํผ ํด๋ฆญ ์ ์์ธ์ ๋ณด ์กฐํ๊ฐ ๋ฉ๋๋ค.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830628-7f1c2d3a-a43c-4fc3-a5c9-743d19070961.png)

 ### ๊ณ์ข์ด์ฒด
 + ๋ณด๋ด๋์ด์ ๊ณ์ข๋ฒํธ๋ ๋ณธ์ธ์ด ๋ณด์ ํ ๊ณ์ข๋ฒํธ๋ฅผ select๋ฐ์ค๋ฅผ ์ด์ฉํด ๋ฏธ๋ฆฌ ๋ณด์ฌ์ค๋๋ค.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830691-12976b12-2d10-4c36-9227-31175363fd62.png)

 ### ์๊ธ
 + ์๊ธ ๊ณ์ข๋ฒํธ๋ ๋ณธ์ธ์ด ๋ณด์ ํ ๊ณ์ข๋ฒํธ๋ฅผ select๋ฐ์ค๋ฅผ ์ด์ฉํด ๋ฏธ๋ฆฌ ๋ณด์ฌ์ค๋๋ค.

![image](https://user-images.githubusercontent.com/103983349/184830737-5abeb2af-cc81-48a5-9c9d-c53c5b54ee4d.png)

 ### ์ถ๊ธ
 + ์ถ๊ธ ๊ณ์ข๋ฒํธ๋ ๋ณธ์ธ์ด ๋ณด์ ํ ๊ณ์ข๋ฒํธ๋ฅผ select๋ฐ์ค๋ฅผ ์ด์ฉํด ๋ฏธ๋ฆฌ ๋ณด์ฌ์ค๋๋ค.

![image](https://user-images.githubusercontent.com/103983349/184830780-6cf00d6b-7180-4f21-862a-e024aee23ba7.png)

 ### ๊ณ์ข ์์ฑ
 + ๊ณ์ข ๋ฏธ์ ํ ์ ์์ฑ ๋ถ๊ฐ๋ฅํฉ๋๋ค.

![image](https://user-images.githubusercontent.com/103983349/184830827-588c02e8-02ca-4756-837e-4aa345074de5.png)

