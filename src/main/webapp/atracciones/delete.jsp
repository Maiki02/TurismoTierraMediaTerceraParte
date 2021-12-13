<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />
<link href="../assets/stylesheets/delete.css" rel="stylesheet" />
</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Borrar una atraccion</h3>
	</header>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>

	<c:if test="${user.esAdmin()}">
		<div id="contenido-tabla">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Nombre</th>
						<th scope="col">Costo</th>
						<th scope="col">Duracion</th>
						<th scope="col">Cupo</th>
						<th scope="col">Tipo de atraccion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${atracciones}" var="atraccion">

						<tr>
							<td>${atraccion.getNombre() }</td>
							<td>${atraccion.getCosto() }</td>
							<td>${atraccion.getDuracion() }</td>
							<td>${atraccion.getCuposDisponibles() }</td>
							<td>${atraccion.getTipoAtraccion() }</td>
							<td>
									
								<div>
								<form action="/TurismoTierraMedia/atracciones/delete.do" method="post">
										<input class="form-control" name="id" placeholder="ID"
											readonly hidden value="${atraccion.getID()}">
										<button type="submit" class="btn btn-danger">Eliminar</button>
								</form>
								</div>
							</td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</body>
</html>