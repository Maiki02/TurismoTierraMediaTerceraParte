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
		<div class="box-container">
			<div class="box">
				<h3>
					<c:out value="${atraccion.getNombre()}"></c:out>
				</h3>
				<br>
				<h4>
					<c:out value="${atraccion.getDescripcion()}"></c:out>
				</h4>
 	<br>

				<p>Costo:<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"
					class="bi bi-coin" viewBox="0 0 16 16">
  <path
						d="M5.5 9.511c.076.954.83 1.697 2.182 1.785V12h.6v-.709c1.4-.098 2.218-.846 2.218-1.932 0-.987-.626-1.496-1.745-1.76l-.473-.112V5.57c.6.068.982.396 1.074.85h1.052c-.076-.919-.864-1.638-2.126-1.716V4h-.6v.719c-1.195.117-2.01.836-2.01 1.853 0 .9.606 1.472 1.613 1.707l.397.098v2.034c-.615-.093-1.022-.43-1.114-.9H5.5zm2.177-2.166c-.59-.137-.91-.416-.91-.836 0-.47.345-.822.915-.925v1.76h-.005zm.692 1.193c.717.166 1.048.435 1.048.91 0 .542-.412.914-1.135.982V8.518l.087.02z" />
  <path
						d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
  <path
						d="M8 13.5a5.5 5.5 0 1 1 0-11 5.5 5.5 0 0 1 0 11zm0 .5A6 6 0 1 0 8 2a6 6 0 0 0 0 12z" />
</svg></p> <h4>${atraccion.getCosto()}</h4>
<br>
				<p>Duracion :<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"
					class="bi bi-smartwatch" viewBox="0 0 16 16">
  <path
						d="M9 5a.5.5 0 0 0-1 0v3H6a.5.5 0 0 0 0 1h2.5a.5.5 0 0 0 .5-.5V5z" />
  <path
						d="M4 1.667v.383A2.5 2.5 0 0 0 2 4.5v7a2.5 2.5 0 0 0 2 2.45v.383C4 15.253 4.746 16 5.667 16h4.666c.92 0 1.667-.746 1.667-1.667v-.383a2.5 2.5 0 0 0 2-2.45V8h.5a.5.5 0 0 0 .5-.5v-2a.5.5 0 0 0-.5-.5H14v-.5a2.5 2.5 0 0 0-2-2.45v-.383C12 .747 11.254 0 10.333 0H5.667C4.747 0 4 .746 4 1.667zM4.5 3h7A1.5 1.5 0 0 1 13 4.5v7a1.5 1.5 0 0 1-1.5 1.5h-7A1.5 1.5 0 0 1 3 11.5v-7A1.5 1.5 0 0 1 4.5 3z" />
</svg></p> <h4>${atraccion.getDuracion()}</h4>
<br>
				<p>Tipo Atraccion:</p><h4>${atraccion.getTipoAtraccion()}</h4> <br>
				<p>Cupos:<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" class="bi bi-people-fill" viewBox="0 0 16 16">
  <path d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
  <path fill-rule="evenodd" d="M5.216 14A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216z"/>
  <path d="M4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
</svg></p> <h4>"${atraccion.getCuposDisponibles()}"</h4> <br>

				<img onerror="this.src='../assets/img/atracciones/Default.jpg';"
					src="../assets/img/atracciones/${atraccion.getNombre()}.jpg">

				<c:choose>
					<c:when
						test="${user.puedeComprar(atraccion) && !atraccion.esProductoYaElecto(user)}">
						<a
							href="/TurismoTierraMedia/atracciones/buy.do?id=${atraccion.getID()}"><button
								type="button" class="btn btn-success">COMPRAR</button></a>
					</c:when>
					<c:otherwise>
						<a href="#"><button type="button"
								class="btn btn-secondary disabled">NO SE PUEDE COMPRAR</button></a>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

	</c:forEach>







</body>
</html>