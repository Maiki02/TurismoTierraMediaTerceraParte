<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<script type="text/javascript" src="../assets/scripts/promociones.js"
	defer></script>
</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Crear una promocion</h3>
	</header>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>

	<c:if test="${user.esAdmin()}">

			<form action="/TurismoTierraMedia/promociones/create.do"
				method="post">

				<div class="mb-3">
					<input class="form-control input-lg" name="nombre"
						placeholder="Nombre Atraccion" value="">
				</div>

				<div class="mb-3">
					<textarea class="form-control" id="exampleFormControlTextarea1"
						name="descripcion" placeholder="Descripcion de la atraccion"
						required rows="3"></textarea>
				</div>

				<div class="mb-3">
					<label>Tipo de atraccion:</label> <select class="form-select seleccion-tipo-atraccion"
						aria-label="Default select example" name="tipo-atraccion" required
						onclick='aparecerAtraccionesInvolucradas(0); ponerAtraccionesEnAXB(0)'>
						<option value="AVENTURA">AVENTURA</option>
						<option value="DEGUSTACION">DEGUSTACION</option>
						<option value="PAISAJE">PAISAJE</option>
					</select>
				</div>

				<div class="btn-group mb-3" role="group" aria-label="Basic example">
					<button type="button" class="btn btn-primary" id="boton-axb"
						onclick="aparecerAXB()">AXB</button>
					<button type="button" class="btn btn-primary" id="boton-absoluta"
						onclick="aparecerAbsoluta()">ABSOLUTA</button>
					<button type="button" class="btn btn-primary" id="boton-porcentual"
						onclick="aparecerPorcentual()">PORCENTUAL</button>
					<input name="tipo-promocion" type="hidden" id="tipo-promocion">
				</div>

				<div class="mb-3">

					<select class="form-select" aria-label="Default select example"
						name="atraccion-premio" id="atraccion-premio">
					</select> <input class="form-control" id="porcentaje-descuento"
						name="porcentaje-descuento" placeholder="Porcentaje descuento"
						type="hidden"> <input class="form-control"
						id="precio-absoluto" name="precio-absoluto"
						placeholder="Precio final" type="hidden">
				</div>


				<div class="mb-3">
					<label>Atracciones involucradas:</label>
					<p class="atracciones-validas"></p>
					<c:forEach items="${atracciones}" var="atraccion">
						<p class="atracciones-involucradas">${atraccion.getNombre()},${atraccion.getTipoAtraccion()}</p>
					</c:forEach>
					<input name="atracciones-involucradas-electas" type="hidden"
						id="atracciones-involucradas-electas" value="">
				</div>



				<div>
					<button type="submit" class="btn btn-success">Crear</button>
				</div>

			</form>

	</c:if>
</body>
</html>