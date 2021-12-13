<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
<jsp:include page="partials/head.jsp"></jsp:include>
<link href="assets/stylesheets/base.css" rel="stylesheet" />
</head>
<body>
	<div class="col-lg-5 mx-auto p-3 py-md-5">

		<main>
			<h1 class="titulo">Registrarse en Turismo en la Tierra Media</h1>


			<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
					</p>
				</div>
			</c:if>

			<form action="registrarse" method="post">

				<div class="mb-3">
					<input class="form-control" name="username" placeholder="Usuario">
				</div>

				<div class="mb-3">
					<input type="password" class="form-control" name="password"
						placeholder="Contraseña">
				</div>
				<div class="mb-3">
					<input type="password" class="form-control" name="password2"
						placeholder="Repetir contraseña">
				</div>
				<div class="mb-3 vh-3">
					<select class="form-select" aria-label="Default select example"
						name="tipo-atraccion-fav">
						<option selected>Tipo de atracción favorito</option>
						<option value="AVENTURA">AVENTURA</option>
						<option value="DEGUSTACION">DEGUSTACION</option>
						<option value="PAISAJE">PAISAJE</option>
					</select>
				</div>

				<div class="mb-3 vh-3">
					<input type="number" class="form-control" name="monedas" step="0.1" min=0 required
						placeholder="Monedas disponibles">
				</div>
				<div class="mb-3 vh-3">
					<input type="number" class="form-control" name="horas" step="0.5" min=0 required
						placeholder="Horas Disponibles">
				</div>



				<div class="d-grid gap-2">
					<button type="submit" class="btn btn-lg btn-primary">Registrarse</button>
				</div>
			</form>

		</main>
	</div>

</body>
</html>