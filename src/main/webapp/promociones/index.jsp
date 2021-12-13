<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />
<link href="../assets/stylesheets/atracciones.css" rel="stylesheet" />
</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Promociones</h3>
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
				<a href="#" class="btn btn-primary" role="button"> <i
					class="bi bi-plus-lg"></i> Nueva Promocion
				</a>
			</div>
			<div class="mb-3">
				<a href="/TurismoTierraMedia/promociones/edit.do" class="btn btn-success" role="button"> <i
					class="bi bi-plus-lg"></i> Editar Promocion
				</a>
			</div>
			<div class="mb-3">
				<a href="/TurismoTierraMedia/promociones/delete.do"
					class="btn btn-danger" role="button"> <i class="bi bi-plus-lg"></i>
					Borrar Promocion
				</a>
			</div>
		</div>
	</c:if>


	<c:forEach items="${promociones}" var="promocion">
		<tr>
			<div class="box-container">
				<div class="box">
					<h3>
						<c:out value="${promocion.getNombre()}"></c:out>
					</h3>
					<p>
						<c:out value="${promocion.getDescripcion()}"></c:out>
					</p>
					<img
						src="../assets/img/<c:out value="${promocion.getID()}"></c:out>.jpeg"
						alt=""> <br>
					<p>
						Costo:
						<c:out value="${promocion.getCosto()}" />
					</p>
					<p>
						Duracion:
						<c:out value="${promocion.getDuracion()}" />
					</p>
					<p>
						Tipo Promocion:
						<c:out value="${promocion.getTipoAtraccion()}" />
					</p>
					<p>
						Atracciones involucradas:
						<c:forEach items="${promocion.getAtracciones()}" var="atraccion">
							<c:out value="atraccion.getNombre()" />
						</c:forEach>
					</p>
					<button type="button" class="btn btn-success">COMPRAR</button>
				</div>

			</div>
		</tr>

	</c:forEach>







</body>

</html>