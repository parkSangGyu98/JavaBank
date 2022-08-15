<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Get Account</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
</head>
<body>

	<c:forEach var="items" items="${account}">
		<h3>${items.customerId}님의 계좌 잔고</h3><br>
	-----------------------------------------------------<br>
		<label>잔액 : </label>${items.balance}원<br>
	</c:forEach>
	
</body>
</html>