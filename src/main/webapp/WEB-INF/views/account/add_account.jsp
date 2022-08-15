<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online bank</title>
<link href="../resources/css/main_page.css" rel="stylesheet">
<link href="../resources/css/addAccount.css" rel="stylesheet">
</head>
<body style="background-color: gray;">



	<%-- header Area --%>
	<div id="header">
		<jsp:include page="../incl/header.jsp">
			<jsp:param name="subtitle"
				value="<%=URLEncoder.encode(\"mod010: mypage.jsp\", \"UTF-8\")%>" />
		</jsp:include>
	</div>
	<%-- container Area --%>
	<main class="form-signin">
		<form action="add_account" method="post">
			<h4 class="h3 mb-3 fw-normal" style="color: white;">계좌종류</h4>
			<input id="typeClick1" type="radio" name="accType" value="S"
				style="margin-bottom: 40px;"><label for="typeClick1"
				style="color: white;"> Saving Account </label> <input
				id="typeClick2" type="radio" name="accType" value="C"><label
				for="typeClick2" style="color: white;"> Checking Account</label>
			<button class="w-100 btn btn-lg btn-primary"
				onclick="window.location.reload()">Create</button>
		</form>
	</main>


	<%-- footer Area --%>
	<div id="footer">
		<%@ include file="../incl/footer.jsp"%>
	</div>
</body>
</html>