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
		<h3 class="titulo">Editar una promocion</h3>
	</header>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>

	<c:if test="${user.esAdmin()}">
		<c:forEach items="${promociones}" var="promocion" varStatus="loop">

			<div class="accordion accordion-flush mb-1"
				id="accordionFlush-${loop.count}">
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-heading-${loop.count}">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#flush-collapse-${loop.count}"
							aria-expanded="false" aria-controls="flush-collapseOne">
							<c:out value="${promocion.getNombre()}"></c:out>
						</button>
					</h2>
					<div id="flush-collapse-${loop.count}"
						class="accordion-collapse collapse"
						aria-labelledby="flush-heading-${loop.count}"
						data-bs-parent="#accordionFlush-${loop.count}">
						<div class="accordion-body">
							<form
								action="/TurismoTierraMedia/promociones/edit.do?id=${promocion.getID()}"
								method="post">

								<div class="mb-3">
									<input class="form-control input-lg" name="nombre"
										placeholder="Nombre Atraccion"
										value="${promocion.getNombre()}">
								</div>
								<div class="mb-3">
									<textarea class="form-control" id="exampleFormControlTextarea1"
										name="descripcion" placeholder="Descripcion de la atraccion"
										required rows="3">${promocion.getDescripcion()}</textarea>
								</div>
								<div class="mb-3">
									<label>Tipo de atraccion:</label> <select class="form-select seleccion-tipo-atraccion"
										aria-label="Default select example" name="tipo-atraccion"
										required onclick='aparecerAtraccionesInvolucradas(${loop.count -1}); ponerAtraccionesEnAXB(${loop.count - 1})'>
										<option value="AVENTURA"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('AVENTURA') }">selected</c:if>>AVENTURA</option>
										<option value="DEGUSTACION"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('DEGUSTACION') }">selected</c:if>>DEGUSTACION</option>
										<option value="PAISAJE"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('PAISAJE') }">selected</c:if>>PAISAJE</option>
									</select>
								</div>

								<div class="btn-group mb-3" role="group"
									aria-label="Basic example">
									<button type="button" class="btn btn-primary" id="boton-axb"
										onclick="aparecerAXB()">AXB</button>
									<button type="button" class="btn btn-primary"
										id="boton-absoluta" onclick="aparecerAbsoluta()">ABSOLUTA</button>
									<button type="button" class="btn btn-primary"
										id="boton-porcentual" onclick="aparecerPorcentual()">PORCENTUAL</button>
									<input name="tipo-promocion" type="hidden" id="tipo-promocion">
								</div>

								<div class="mb-3">

									<select class="form-select" aria-label="Default select example"
										name="atraccion-premio" id="atraccion-premio">
									</select> <input class="form-control" id="porcentaje-descuento"
										name="porcentaje-descuento" placeholder="Porcentaje descuento"
										type="hidden" value=""> <input class="form-control"
										id="precio-absoluto" name="precio-absoluto"
										placeholder="Precio final" type="hidden" value="">
								</div>


								<div class="mb-3">
									<label>Atracciones involucradas:</label>
									<p class="atracciones-validas"></p>
									<input name="atracciones-involucradas-electas" type="hidden"
										id="atracciones-involucradas-electas" value="">
								</div>



								<div>
									<button type="submit" class="btn btn-success">Guardar</button>
								</div>

							</form>
						</div>
					</div>
				</div>

			</div>
		</c:forEach>
		<c:forEach items="${atracciones}" var="atraccion">
			<p class="atracciones-involucradas">${atraccion.getNombre()},${atraccion.getTipoAtraccion()}</p>
		</c:forEach>
	</c:if>
</body>
</html>