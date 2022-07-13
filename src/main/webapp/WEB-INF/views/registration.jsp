<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="${contextRoot}/tracking" method="post"
		modelAttribute="user">

		<form:hidden path="id" />
		<form:input path="name" placeholder="Enter user name" />
		<form:input path="cryptocurrencySymbol"
			placeholder="Enter cryptocurrency symbol" />

		<button type="submit">Track</button>

	</form:form>

</body>
</html>