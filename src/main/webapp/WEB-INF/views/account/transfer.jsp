<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title href="#">Online bank</title>
<link href="../resources/css/main_page.css" rel="stylesheet">
<link href="../resources/css/transfer.css" rel="stylesheet">
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
	</main>

	<%-- footer Area --%>
	<div id="footer">
		<%@ include file="../incl/footer.jsp"%>
	</div>
</body>
</html>