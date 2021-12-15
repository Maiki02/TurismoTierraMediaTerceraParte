<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<link href="assets/stylesheets/login.css" rel="stylesheet"/>
<link href="assets/stylesheets/base.css" rel="stylesheet" />
</head>
<body>
	<div class="col-lg-5 mx-auto p-3 py-md-5">

		<main>
			<h1 class="titulo" >Turismo en la Tierra Media</h1>


			<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
					</p>
				</div>
			</c:if>

			<form action="login" method="post">

				<div class="mb-3">
					<!--  <label for="username" class="form-label"></label> -->
					<input class="form-control" name="username" placeholder="Usuario">
				</div>

				<div class="mb-3">
					<input type="password" class="form-control" name="password" placeholder="Contraseña">
				</div>

				<div class="d-grid gap-2">
					<button type="submit" class="btn btn-lg btn-success">Ingresar</button>
					<input type="button" value="Registrarse" class="btn btn-lg btn-success" 
					onclick = "location='registro.jsp'"/>
					<input type="button" value="Ingresar como invitado" class="btn btn-lg btn-warning" 
					onclick = "location='/TurismoTierraMedia/invitado'"/>
				</div>
			</form>
		
		</main>
	</div>
</body>
</html>