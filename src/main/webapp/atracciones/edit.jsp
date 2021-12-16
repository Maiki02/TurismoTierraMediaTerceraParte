<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>
<link href="../assets/stylesheets/base.css" rel="stylesheet" />

</head>

<body>
	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<header>
		<h3 class="titulo">Editar una atraccion</h3>
	</header>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>

	<c:if test="${user.esAdmin()}">

		<c:forEach items="${atracciones}" var="atraccion" varStatus="loop">

			<div class="accordion accordion-flush mb-1"
				id="accordionFlush-${loop.count}" style="width:80%; margin:auto;">
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-heading-${loop.count}">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#flush-collapse-${loop.count}"
							aria-expanded="false" aria-controls="flush-collapseOne">
							<c:out value="${atraccion.getNombre()}"></c:out>
						</button>
					</h2>
					<div id="flush-collapse-${loop.count}"
						class="accordion-collapse collapse"
						aria-labelledby="flush-heading-${loop.count}"
						data-bs-parent="#accordionFlush-${loop.count}">
						<div class="accordion-body">
							<form action="/TurismoTierraMedia/atracciones/edit.do"
								method="post">

								<div class="mb-3">
									<input class="form-control" name="id" placeholder="ID" readonly
										type="hidden" value="${atraccion.getID()}">
								</div>

								<div class="mb-3">
									<input class="form-control input-lg" name="nombre"
										placeholder="Nombre Atraccion"
										value="${atraccion.getNombre()}">
								</div>

								<div class="mb-3">
									<input type="number" class="form-control input-sm" name="costo"
										step="0.1" min=0 required placeholder="Costo"
										value="${atraccion.getCosto()}">
								</div>
								<div class="mb-3">
									<input type="number" class="form-control" name="duracion"
										step="0.5" min=0 required placeholder="Duracion"
										value="${atraccion.getDuracion()}">
								</div>
								<div class="mb-3">
									<input type="number" class="form-control" name="capacidad"
										step="any" min=1 required placeholder="Capacidad"
										value="${atraccion.getCuposDisponibles()}" value="">
								</div>

								<div class="mb-3">
									<select class="form-select" aria-label="Default select example"
										name="tipo-atraccion" required>
										<option value="AVENTURA"
											<c:if test="${atraccion.getTipoAtraccion().toString().equals('AVENTURA') }">selected</c:if>>AVENTURA

										
										<option value="DEGUSTACION"
											<c:if test="${atraccion.getTipoAtraccion().toString().equals('DEGUSTACION') }">selected</c:if>>DEGUSTACION</option>
										<option value="PAISAJE"
											<c:if test="${atraccion.getTipoAtraccion().toString().equals('PAISAJE') }">selected</c:if>>PAISAJE</option>
									</select>
								</div>
								<div class="mb-3">
									<textarea class="form-control" id="exampleFormControlTextarea1"
										name="descripcion" placeholder="Descripcion de la atraccion"
										required rows="3">${atraccion.getDescripcion()}</textarea>
								</div>

								<div>
									<button type="submit" class="btn btn-success">Editar</button>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</c:forEach>

	</c:if>
</body>
</html>