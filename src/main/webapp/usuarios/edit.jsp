<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.11.3/datatables.min.css"/>
 
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.11.3/datatables.min.js"></script>
</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Editar un usuario</h3>
	</header>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>

	<c:if test="${user.esAdmin()}">


		<form action="/TurismoTierraMedia/usuarios/edit.do" method="post">

			<div class="mb-3">
			
				<input class="form-control" name="id" placeholder="ID" readonly
					type="hidden" value="${usuario.getID()}">
			</div>

			<div class="mb-3">
			<label>Nombre:</label>
				<input class="form-control input-lg" name="nombre"
					placeholder="Nombre usuario" value="${usuario.getNombre()}">
			</div>

			<div class="mb-3">
			<label>Monedas Disponibles:</label>
				<input type="number" class="form-control input-sm" name="monedas"
					step="0.1" min=0 required placeholder="Monedas Disponibles"
					value="${usuario.getMonedasDisponibles()}">
			</div>
			<div class="mb-3">
			<label>Horas Disponibles:</label>
				<input type="number" class="form-control" name="tiempo" step="0.5"
					min=0 required placeholder="Tiempo Disponible"
					value="${usuario.getHorasDisponibles()}">
			</div>
			<div class="mb-3">
			<label>Total a pagar:</label>
				<input type="number" class="form-control" name="total-a-pagar"
					step="0.1" min=0 required placeholder="Total a pagar"
					value="${usuario.getTotalAPagar()}">
			</div>
			<div class="mb-3">
			<label>Total horas gastadas:</label>
				<input type="number" class="form-control" name="total-horas-gastadas"
					step="0.5" min=0 required placeholder="Total de horas gastadas"
					value="${usuario.getTotalHorasGastadas()}">
			</div>

			<div class="mb-3">
			<label>Tipo favorito:</label>
				<select class="form-select" aria-label="Default select example"
					name="tipo-favorito" required>
					<option value="AVENTURA"
						<c:if test="${usuario.getTipoFavorito().toString().equals('AVENTURA') }">selected</c:if>>AVENTURA
					<option value="DEGUSTACION"
						<c:if test="${usuario.getTipoFavorito().toString().equals('DEGUSTACION') }">selected</c:if>>DEGUSTACION</option>
					<option value="PAISAJE"
						<c:if test="${usuario.getTipoFavorito().toString().equals('PAISAJE') }">selected</c:if>>PAISAJE</option>
				</select>
			</div>
			<div class="mb-3">
			<label>Es admin:</label>
				<select class="form-select" aria-label="Default select example"
					name="es-admin" required>
					<option value="SI"
						<c:if test="${usuario.esAdmin()}">selected</c:if>>SI</option>
					<option value="NO"
						<c:if test="${usuario.esAdmin()}">selected</c:if>>NO</option>
					
				</select>
			</div>

			<div>
				<button type="submit" class="btn btn-success">Guardar</button>
			</div>
		</form>

	</c:if>
</body>
</html>