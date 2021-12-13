<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../partials/head.jsp"></jsp:include>

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
		<c:forEach items="${promociones}" var="promocion">
			<div class="accordion accordion-flush mb-1"
				id="accordionFlushExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingOne">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
							aria-expanded="false" aria-controls="flush-collapseOne">
							<c:out value="${promocion.getNombre()}"></c:out>
						</button>
					</h2>
					<div id="flush-collapseOne" class="accordion-collapse collapse"
						aria-labelledby="flush-headingOne"
						data-bs-parent="#accordionFlushExample">
						<div class="accordion-body">
							<form action="/TurismoTierraMedia/promociones/edit.do"
								method="post">
						
								<div class="mb-3">
									<input class="form-control" name="id" placeholder="ID" readonly
										hidden value="${promocion.getID()}">
								</div>

								<div class="mb-3">
									<input class="form-control input-lg" name="nombre"
										placeholder="Nombre Atraccion"
										value="${promocion.getNombre()}">
								</div>

								<div class="mb-3">
									<textarea class="form-control" id="exampleFormControlTextarea1"
										name="descripcion" placeholder="Descripcion de la atraccion"
										required rows="3">${atraccion.getDescripcion()}</textarea>
								</div>
								
								<div class="mb-3">
									<label>Tipo de promocion:</label> <select class="form-select"
										aria-label="Default select example" name="tipo-atraccion"
										required>
										<option value="AXB"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('AVENTURA') }">selected</c:if>>AXB
										
										<option value="PORCENTUAL"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('DEGUSTACION') }">selected</c:if>>PORCENTUAL</option>
										<option value="ABSOLUTA"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('PAISAJE') }">selected</c:if>>ABSOLUTA</option>
									</select>
								</div>
								
								<div class="mb-3">
									<label>Tipo de atraccion:</label> <select class="form-select"
										aria-label="Default select example" name="tipo-atraccion"
										required>
										<option value="AVENTURA"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('AVENTURA') }">selected</c:if>>AVENTURA
										
										<option value="DEGUSTACION"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('DEGUSTACION') }">selected</c:if>>DEGUSTACION</option>
										<option value="PAISAJE"
											<c:if test="${promocion.getTipoAtraccion().toString().equals('PAISAJE') }">selected</c:if>>PAISAJE</option>
									</select>
								</div>
								<div class="mb-3">
									<label>Atracciones involucradas:</label> 
									<select
										class="form-select" aria-label="Default select example"
										name="premio" required>
										<c:forEach items="${atracciones}" var="atraccion">
											<c:if
												test="${atraccion.getTipoAtraccion() == promocion.getTipoAtraccion()}">
												<option value="${atraccion.getNombre()}">${atraccion.getNombre()}</option>
											</c:if>

										</c:forEach>
									</select>
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

	</c:if>
</body>
</html>