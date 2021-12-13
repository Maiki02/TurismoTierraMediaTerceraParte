<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="mb-3">
		<input class="form-control input-lg" name="nombre"
			placeholder="Nombre Atraccion" >
	</div>

	<div class="mb-3">
		<input type="number" class="form-control input-sm" name="costo" step="0.1"
			min=0 required placeholder="Costo" >
	</div>
	<div class="mb-3">
		<input type="number" class="form-control" name="duracion" step="0.5"
			min=0 required placeholder="Duracion">
	</div>
	<div class="mb-3">
		<input type="number" class="form-control" name="capacidad" step="any"
			min=1 required placeholder="Capacidad">
	</div>

	<div class="mb-3">
		<select class="form-select" aria-label="Default select example"
			name="tipo-atraccion" required>
			<option value="AVENTURA">AVENTURA</option>
			<option value="DEGUSTACION">DEGUSTACION</option>
			<option value="PAISAJE">PAISAJE</option>
		</select>
	</div>

	<div class="mb-3">
		<textarea class="form-control" id="exampleFormControlTextarea1" name="descripcion"
			placeholder="Descripcion de la atraccion" required rows="3"></textarea>
	</div>


	<div>
		<button type="submit" class="btn btn-primary">Guardar</button>
		<a onclick="window.history.back();" class="btn btn-secondary"
			role="button">Cancelar</a>
	</div>


