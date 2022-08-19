# 🏦 JavaBank
+ 기본적인 은행 서비스를 구현한 개인프로젝트입니다.
 + 2022.7.25 ~ 2022.7.31
## 사용한 기술 및 환경
+ Window
+ eclipse
+ Java
+ Spring
+ Jsp
+ MySQL
+ HTML/CSS3
+ Javascript
+ bootstrap

## 구현 기능
+ 계좌 
  + 랜덤한 계좌번호인 적금 또는 대출 계좌 생성
  + 계좌 내역 조회 및 계좌 상세내역 조회
+ 사용자
  + 회원가입, 로그인, 로그아웃
  + 계좌이체
  + 입금, 출금
  
 ## 주요 코드
 ### 계좌
 + 랜덤 계좌번호 생성
   1. 랜덤함수를 이용하여 "123-45-6789" 형태의 숫자를 가져옵니다.
   2. 계좌를 생성할 때 SavingAccount 선택 시 "S"를, CheckingAccount 선택 시 "C"를 받아와 알맞는 계좌를 생성해주며 DB에 저장다.
 
           AddAccountController 일부

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

+ 계좌 상세내역 조회
  1. 버튼을 클릭하기전의 jsp 에서 컨텐츠 부분만 바꿔서 작업하였습니다.
  2. 로그인 시 등록한 session값(로그인한 ID) 를 이용하여 DB에서 보유중인 계좌정보를 가져옵니다.
  3. 가져온 계좌정보들을 for문을 이용하여 상세정보를 조회시킵니다.
  4. 돌아가기 버튼 클릭시 main_page 컨트롤러로 이동합니다.

           get_Detail.jsp 일부

           <form action="main_page" method="get" style="text-align: center; margin-bottom: 50px;">
			       <button class="card-text mb-auto getBalanceButton"
              type="submit">돌아가기</button>
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
                 <div class="mb-1">잔고 : ${account.balance}원</div>
                 <div class="mb-1">이자율 : ${account.interestRate}%</div>
                 <div class="mb-1">대출한도 : ${account.overAmount}원</div>

                </div>
               </div>
              </div>
             </c:forEach>

            </div>
	
### 사용자
+ 로그인
  1. 입력한 아이디 비밀번호와 DB에 저장된 아이디 비밀번호의 일치여부를 확인한 뒤 로그인 시킵니다.
  2. 유효성검사를 통과한 경우 로그인한 사용자의 ID를 session에 저장시킵니다.
	
		    LoginController 일부

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
    
    3. 실패 시 화면
    
    ![image](https://user-images.githubusercontent.com/103983349/184834152-bd2073e6-9961-41de-9da7-7a7466661dcb.png)

+ 회원가입
  1. 입력한 정보들의 공백 여부를 확인합니다.
  2. DB내 저장된 ID와 입력한 ID의 중복 여부를 확인합니다.
  3. 이상 없을 시, DB에 정보를 저장함과 동시에 로그인 페이지로 화면을 전환합니다.

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
				CustomerService.context.close();
				return "customer/login";
			}


+ 계좌이체
  1. 본인 계좌를 입력하는 번거로움을 감안하여 session을 이용해 현재 로그인 한 유저가 보유한 계좌번호들을 select 박스를 이용해 미리 보여주며 선택할 수 있도록 합니다.
  2. 계좌선택을 안할 경우, 비밀번호 오류, 금액입력 오류, 동일 계좌로의 이체를 할 경우에 유효성 검사를 하였습니다.
  3. 이상 없을 시 보내는이의 계좌에 출금 기능을, 받는이의 계좌에 입금 기능을 불러왔습니다. (입금, 출금 구현할때 만들어 두었던 메소드)
			
			transfer.jsp 일부
			
			<form action="transfer" method="post">
			<h1 class="h3 mb-3 fw-normal" style="text-align:center;">계좌이체</h1>

			<div class="form-floating">
				<select name="sendAccountNum" class="form-control" style="padding-top : 0.8rem;">
				    <option value="">보내는 이 계좌번호</option>
				    <c:forEach var="account" items="${accountNum}">
				    		<option value="${account.accountNum}">${account.accountNum}</option>
				    </c:forEach>
				</select>
			</div>
			<div class="form-floating">
				<input type="password" name="passwd" class="form-control"
					id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">비밀번호 </label>
			</div>
			<div class="form-floating">
				<input type="text" name="getAccountNum"
					placeHolder="000-00-0000" class="form-control"
					id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">받는 이 계좌번호 </label>
			</div>
			<div class="form-floating margin">
				<input type="number" name="money" class="form-control"
					id="floatingPassword" placeholder="Password">
				<label for="floatingPassword">이체 금액 </label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit">이체</button>
			</form>



			TransferController 일부

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
	    

 ## 구현 화면
 ### 로그인
 + 로그인 실패 시 alert을 이용해 경고문을 띄우고 다시 로그인 화면으로 보냅니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184826589-0afcb32a-c4d8-43aa-b67f-0ff5a377ee79.png)

 ### 회원가입
 + ID중복확인은 회원가입 버튼 클릭 시 작동합니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184826760-46824b75-cee6-4045-8255-10738db5c616.png)

 ### 메인
 + 로그인 하자마자 볼수있는 화면으로, 본인이 보유한 계좌를 보여줍니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830508-fc254f54-6d00-46d7-8b95-c2cac0c72b1c.png)

 ### 상세조회
 + 중간 상세조회 버튼 클릭 시 상세정보 조회가 됩니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830628-7f1c2d3a-a43c-4fc3-a5c9-743d19070961.png)

 ### 계좌이체
 + 보내는이의 계좌번호는 본인이 보유한 계좌번호를 select박스를 이용해 미리 보여줍니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830691-12976b12-2d10-4c36-9227-31175363fd62.png)

 ### 입금
 + 입금 계좌번호는 본인이 보유한 계좌번호를 select박스를 이용해 미리 보여줍니다.

![image](https://user-images.githubusercontent.com/103983349/184830737-5abeb2af-cc81-48a5-9c9d-c53c5b54ee4d.png)

 ### 출금
 + 출금 계좌번호는 본인이 보유한 계좌번호를 select박스를 이용해 미리 보여줍니다.

![image](https://user-images.githubusercontent.com/103983349/184830780-6cf00d6b-7180-4f21-862a-e024aee23ba7.png)

 ### 계좌 생성
 + 계좌 미선택 시 생성 불가능합니다.

![image](https://user-images.githubusercontent.com/103983349/184830827-588c02e8-02ca-4756-837e-4aa345074de5.png)

