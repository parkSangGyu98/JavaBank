<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Check Account</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
</head>
<body>
	<h3>${account.customerId}님의 계좌목록</h3><br>
	-----------------------------------------------------<br>
	<c:forEach var="account" items="${accountArr}">
		<label>계좌번호 : </label>${account.accountNum}<br>
		<label>계좌타입 : </label>${account.accType}<br>
		<label>잔액 : </label>${account.balance}원<br>
		<label>이자율 : </label>${account.interestRate}%<br>
		<label>대출한도 : </label>${account.overAmount}원<br>
		-----------------------------------------------------<br>
	</c:forEach>
	
</body>
</html>