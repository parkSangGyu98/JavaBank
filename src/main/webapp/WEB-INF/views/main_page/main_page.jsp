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
	<main class="container">
	<h1>My Account</h1>
		<form action="get_Detail" method="get" style="text-align: center; margin-bottom: 50px;">
			<button class="card-text mb-auto getBalanceButton"
				type="submit">상세조회</button>
		</form>

		<div class="row mb-2">

			<c:forEach var="account" items="${accountNum}">
				<div class="col-md-6">
					<div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">

							<c:if test="${account.accType eq 'S'}">
								<h3 class="mb-0">Saving Account</h3>
							</c:if>
							<c:if test="${account.accType eq 'C'}">
								<h3 class="mb-0">Checking Account</h3>
							</c:if>
							<p class="card-text mb-auto">${account.accountNum}</p>
							<div class="mb-1">개설일 ${account.regDate}</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</main>
	<%-- footer Area --%>
	<div id="footer">
		<%@ include file="../incl/footer.jsp"%>
	</div>
</body>
</html>