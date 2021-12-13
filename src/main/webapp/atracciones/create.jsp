<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>

	<main class="container">
		<h1 class="titulo">Crear atraccion</h1>

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
				</p>
			</div>
		</c:if>
		<form action="/TurismoTierraMedia/atracciones/create.do" method="post">
			<jsp:include page="form.jsp"></jsp:include>
		</form>


	</main>

</body>
</html>