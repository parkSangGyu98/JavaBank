<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Account</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
</head>
<body>
	<h3>ID로 계좌목록정보 확인</h3>
	<form action="check_account" method="post">
		<label>회원ID </label><input type="text" name="customerId"><br>
		<input type="submit" value="조회"/>
	</form>
</body>
</html>