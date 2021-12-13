<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<link href="assets/stylesheets/base.css" rel="stylesheet" />
<link href="assets/stylesheets/normalize.css" rel="stylesheet" />
</head>
<body>
	
	<jsp:include page="partials/nav.jsp"></jsp:include>

	<main class="container">
		<div class="bg-light p-4 rounded">
			<h1>
				¡Bienvenido, <c:out value="${user.nombre}" />!
			</h1>
		</div>
	</main>
</body>
</html>
