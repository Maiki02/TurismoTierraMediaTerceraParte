<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav id="nav" class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/TurismoTierraMedia/index.jsp">Tierra
			Media</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="menu collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<li class="nav-item"><a class="nav-link"
					href="/TurismoTierraMedia/atracciones/index.do">Atracciones</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/TurismoTierraMedia/promociones/index.do">Promociones</a></li>
				<c:if test="${user.esAdmin()}">
					<li class="nav-item"><a class="nav-link"
						href="/TurismoTierraMedia/usuarios/info.do">Usuarios</a></li>
				</c:if>


			</ul>


			<ul class="navbar-nav">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<c:out value="${user.getNombre()}"></c:out>
				</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="navbarDropdown">
						<li><a href="/TurismoTierraMedia/usuarios/perfil.do"
							class="dropdown-item">Perfil</a></li>

						<li><a class="dropdown-item disabled" style="color: black;">
								<i title="monedas" style="color: gold;" class="bi bi-coin"></i>
								<c:out value="${user.getMonedasDisponibles()}"></c:out>
						</a></li>
						<li><a class="dropdown-item disabled" style="color: black;">
								<i title="tiempo" style="color: blue;" class="bi bi-clock-fill"></i>
								<c:out value="${user.getHorasDisponibles()}h"></c:out>
						</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a href="/TurismoTierraMedia/logout"
							class="dropdown-item">Salir</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>