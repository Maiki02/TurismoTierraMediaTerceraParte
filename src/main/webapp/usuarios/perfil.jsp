<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />
<link href="../assets/stylesheets/perfil.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<main class="container">
		<h3 class="titulo">
			Datos del perfil de
			<c:out value="${usuario.getNombre()}" />
		</h3>

		<div class="Datos">

			<p>Le quedan:</p>
			<h4>
				<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"
					class="bi bi-coin" viewBox="0 0 16 16">
  <path
						d="M5.5 9.511c.076.954.83 1.697 2.182 1.785V12h.6v-.709c1.4-.098 2.218-.846 2.218-1.932 0-.987-.626-1.496-1.745-1.76l-.473-.112V5.57c.6.068.982.396 1.074.85h1.052c-.076-.919-.864-1.638-2.126-1.716V4h-.6v.719c-1.195.117-2.01.836-2.01 1.853 0 .9.606 1.472 1.613 1.707l.397.098v2.034c-.615-.093-1.022-.43-1.114-.9H5.5zm2.177-2.166c-.59-.137-.91-.416-.91-.836 0-.47.345-.822.915-.925v1.76h-.005zm.692 1.193c.717.166 1.048.435 1.048.91 0 .542-.412.914-1.135.982V8.518l.087.02z" />
  <path
						d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  <path
						d="M8 13.5a5.5 5.5 0 1 1 0-11 5.5 5.5 0 0 1 0 11zm0 .5A6 6 0 1 0 8 2a6 6 0 0 0 0 12z" />
</svg>
				<c:out value="${usuario.getMonedasDisponibles()}" />
				monedas
			</h4>
			<hr>
			<br>
			<p>Sus horas restantes son:</p>
			<h4>
				<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"
					class="bi bi-smartwatch" viewBox="0 0 16 16">
  <path
						d="M9 5a.5.5 0 0 0-1 0v3H6a.5.5 0 0 0 0 1h2.5a.5.5 0 0 0 .5-.5V5z" />
  <path
						d="M4 1.667v.383A2.5 2.5 0 0 0 2 4.5v7a2.5 2.5 0 0 0 2 2.45v.383C4 15.253 4.746 16 5.667 16h4.666c.92 0 1.667-.746 1.667-1.667v-.383a2.5 2.5 0 0 0 2-2.45V8h.5a.5.5 0 0 0 .5-.5v-2a.5.5 0 0 0-.5-.5H14v-.5a2.5 2.5 0 0 0-2-2.45v-.383C12 .747 11.254 0 10.333 0H5.667C4.747 0 4 .746 4 1.667zM4.5 3h7A1.5 1.5 0 0 1 13 4.5v7a1.5 1.5 0 0 1-1.5 1.5h-7A1.5 1.5 0 0 1 3 11.5v-7A1.5 1.5 0 0 1 4.5 3z" />
</svg>
				<c:out value="${usuario.getHorasDisponibles()}" />

			</h4>
			<hr>
			<br>
			<p>Su genero favorito es:</p>
			<h4>
				<c:out value="${usuario.getTipoFavorito() }" />
			</h4>
			<hr>
			<br>

			<c:if test="${usuario.getTotalAPagar() == 0}">
				<h4>El usuario no tiene que pagar nada, por lo tanto no tiene
					horas para gastar.</h4>
			</c:if>
			<hr>
			<br>

			<c:if test="${usuario.getTotalAPagar() > 0}">


				<p>Deberá pagar</p>
				<h4>
					<c:out value="${usuario.getTotalAPagar()}" />
				</h4>
				<p>, gastando un total de</p>

				<h4>
					<c:out value="${usuario.getHorasGastadas() }" />
				</h4>
				<p>horas</p>
				<hr>
			</c:if>
			<c:if test="${usuario.getProductosComprados().size() > 0 }">
				<p>El usuario compró las siguientes atracciones/promociones:</p>
				<h4>
					<c:forEach items="${usuario.getProductosComprados()}" var="producto">
						<c:out value="${producto }" /> <br/><br>
					</c:forEach>
				</h4>
			</c:if>
		</div>
		</main>
</body>
</html>