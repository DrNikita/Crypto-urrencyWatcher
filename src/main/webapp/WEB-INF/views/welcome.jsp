<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CryptoApp</title>
</head>
<body>

	<h1>Cryptocurrency watcher</h1>

	<c:forEach var="cryptocurrency" items="${cryptocurrencies}">
		<p>
			<br>{id}${cryptocurrency.id}; {symbol}${cryptocurrency.symbol};
			{value}${cryptocurrency.value}$$.
		</p>
	</c:forEach>

	<br>

	<form action="${contextRoot}/currentvalue">
		<label for="symbol">Enter cryptocurrency symbol</label> <input
			type="text" name="symbol">
		<button type="submit">Show actual price</button>
	</form>

	<br>

	<a href="${contextRoot}/tracking">Tracking</a>

</body>
</html>