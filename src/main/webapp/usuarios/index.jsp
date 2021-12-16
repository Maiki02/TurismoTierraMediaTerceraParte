<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />
<link href="../assets/stylesheets/tabla.css" rel="stylesheet" />
</head>
<body>

	<c:if test="${user.esAdmin()}">
		<jsp:include page="../partials/nav.jsp"></jsp:include>

		<main class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Nombre</th>
						<th scope="col">Monedas Restantes</th>
						<th scope="col">Tiempo Restante</th>
						<th scope="col">Tipo Favorito</th>
						<th scope="col">Total a pagar</th>
						<th scope="col">Total de horas</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usuarios}" var="usuario">

						<tr>
							<td>${usuario.getNombre() }</td>
							<td>${usuario.getMonedasDisponibles() }</td>
							<td>${usuario.getHorasDisponibles() }</td>
							<td>${usuario.getTipoFavorito() }</td>
							<td>${usuario.getTotalAPagar() }</td>
							<td>${usuario.getHorasGastadas() }</td>
							<td>
								<div>
									<a href="/TurismoTierraMedia/usuarios/perfil.do?id=${usuario.getID()}"><button
											type="submit" class="btn btn-warning">INFO</button> </a>
								</div>
								<div>
									<a href="/TurismoTierraMedia/usuarios/delete.do?id=${usuario.getID() }"><button
											type="submit" class="btn btn-danger">ELIMINAR</button> </a>
								</div>
								<div>
									<a href="/TurismoTierraMedia/usuarios/edit.do?id=${usuario.getID() }"><button
											type="submit" class="btn btn-success">EDITAR</button> </a>
								</div>
							</td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</main>

	</c:if>
</body>
</html>