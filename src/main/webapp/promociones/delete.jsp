<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/delete.css" rel="stylesheet" />
</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Borrar una promoción</h3>
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
						<th scope="col">Tipo de atraccion</th>
						<th scope="col">Tipo de promocion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${promociones}" var="promocion">

						<tr>
							<td>${promocion.getNombre() }</td>
							<td>${promocion.getCosto() }</td>
							<td>${promocion.getDuracion() }</td>
							<td>${promocion.getTipoAtraccion() }</td>
							<td>${promocion.getTipoPromocion() }</td>
							<td>
								<div>
									<form action="/TurismoTierraMedia/promociones/delete.do"
										method="post">
										<input class="form-control" name="id" placeholder="ID"
											readonly hidden value="${promocion.getID()}">
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