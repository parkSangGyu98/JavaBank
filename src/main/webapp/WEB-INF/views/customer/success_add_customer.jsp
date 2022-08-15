<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Add Customer</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
</head>
<body>
	<h3>회원가입성공</h3>
		<label>아이디 </label>${customer.id}<br>
		<label>비밀번호 </label>${customer.passwd}<br>
		<label>이름 </label>${customer.name}<br>
		<label>주민번호 </label>${customer.ssn}<br>
		<label>연락처 </label>${customer.phone}<br>
</body>
</html>