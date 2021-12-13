<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/atracciones.css" rel="stylesheet" />
</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Atracciones</h3>
	</header>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
				<c:if test="${errors != null}">
					<ul>
						<c:forEach items="${errors}" var="entry">
							<li><c:out value="${entry.getValue()}"></c:out></li>
						</c:forEach>
					</ul>
				</c:if>
			</p>
		</div>
	</c:if>

	<c:if test="${user.esAdmin()}">
		<div id="botones">
			<div class="mb-3">
				<a href="/TurismoTierraMedia/atracciones/create.do"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Atracción
				</a>
			</div>
			<div class="mb-3">
				<a href="/TurismoTierraMedia/atracciones/edit.do"
					class="btn btn-success" role="button"> <i class="bi bi-plus-lg"></i>
					Editar Atracción
				</a>
			</div>
			<div class="mb-3">
				<a href="/TurismoTierraMedia/atracciones/delete.do"
					class="btn btn-danger" role="button"> <i class="bi bi-plus-lg"></i>
					Borrar Atracción
				</a>
			</div>
		</div>
	</c:if>


	<c:forEach items="${atracciones}" var="atraccion">
		<tr>
			<div class="box-container">
				<div class="box">
					<h3>
						<c:out value="${atraccion.getNombre()}"></c:out>
					</h3>
					<p>
						<c:out value="${atraccion.getDescripcion()}"></c:out>
					</p>
					<img
						src="../assets/img/<c:out value="${atraccion.getID()}"></c:out>.jpeg"
						alt=""> <br>
						<p>Costo: "${atraccion.getCosto()}"</p>
						<p>Duracion: "${atraccion.getDuracion()}"</p>
						<p>Tipo Atraccion: "${atraccion.getTipoAtraccion()}"</p>
						<p>Cupos: "${atraccion.getCuposDisponibles()}"</p>
					<button type="button" class="btn btn-success">COMPRAR</button>
				</div>

			</div>
		</tr>

	</c:forEach>







</body>
</html>