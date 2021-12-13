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
		<h3 class="titulo">
			Datos del perfil de
			<c:out value="${usuario.getNombre()}" />
		</h3>
		<p>
			Le quedan:
			<c:out value="${usuario.getMonedasDisponibles()}" />
			monedas
		</p>
		<p>
			Sus horas restantes son
			<c:out value="${usuario.getHorasDisponibles()}" />
		</p>
		<p>
			Su genero favorito es:
			<c:out value="${usuario.getTipoFavorito() }" />
		</p>
		<c:if test="${usuario.getTotalAPagar() == 0}">
			<p>El usuario no tiene que pagar nada, por lo tanto no tiene
				horas para gastar.</p>
		</c:if>
		<c:if test="${usuario.getTotalAPagar() > 0}">
			<p>
				Deberá pagar
				<c:out value="${usuario.getTotalAPagar()}" />
				, gastando un total de
				<c:out value="${usuario.getHorasGastadas() }" />
				horas
			</p>
		</c:if>
		<c:if test="${usuario.getProductosComprados().size() > 0 }">
			<p>El usuario compró las siguientes atracciones/promociones:</p>
			<c:forEach items="${user.getProductosComprados()}" var="producto">
				<p>${producto}</p>

			</c:forEach>
		</c:if>
</body>
</html>