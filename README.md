# JavaBank
+ 기본적인 은행 서비스를 구현한 개인프로젝트입니다.
 + 2022.7.- ~ 2022.7.-
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
   2. 계좌를 생성할 때 SavingAccount 선택 시 "S"를, CheckingAccount 선택 시 "C"를 받아와 알맞는 계좌를 생성해주며 DB에 저장한다.
 
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


 ## 구현 화면
 ### 로그인
 
 ![image](https://user-images.githubusercontent.com/103983349/184826589-0afcb32a-c4d8-43aa-b67f-0ff5a377ee79.png)

 ### 회원가입
 
 ![image](https://user-images.githubusercontent.com/103983349/184826760-46824b75-cee6-4045-8255-10738db5c616.png)

 ### 메인
 + 본인이 보유한 계좌를 보여줍니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830508-fc254f54-6d00-46d7-8b95-c2cac0c72b1c.png)

 ### 상세조회
 + 중간 상세조회 버튼 클릭 시 상세정보 조회가 됩니다.
 
 ![image](https://user-images.githubusercontent.com/103983349/184830628-7f1c2d3a-a43c-4fc3-a5c9-743d19070961.png)

 ### 계좌이체
 
 ![image](https://user-images.githubusercontent.com/103983349/184830691-12976b12-2d10-4c36-9227-31175363fd62.png)

 ### 입금

![image](https://user-images.githubusercontent.com/103983349/184830737-5abeb2af-cc81-48a5-9c9d-c53c5b54ee4d.png)

 ### 출금

![image](https://user-images.githubusercontent.com/103983349/184830780-6cf00d6b-7180-4f21-862a-e024aee23ba7.png)

 ### 계좌 생성

![image](https://user-images.githubusercontent.com/103983349/184830827-588c02e8-02ca-4756-837e-4aa345074de5.png)

