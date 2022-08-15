<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Balance</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
</head>
<body>
	<h3>계좌번호로 잔고 확인</h3>
	<form action="get_balance" method="post">
		<label>회원 계좌번호 </label><input type="text" name="accountNum" placeHolder="000-00-0000"><br>
		<input type="submit" value="잔고 조회"/>
	</form>
</body>
</html>