<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<link href="assets/stylesheets/base.css" rel="stylesheet" />
<link href="assets/stylesheets/inicio.css" rel="stylesheet" />
</head>
<body>

	<jsp:include page="partials/nav.jsp"></jsp:include>

	<header class="encabezado">
		<h1>¡Bienvenido/a a la Tierra Media, ${user.getNombre()}!</h1>
		<h2>Vive una magica aventura en familia</h2>
	</header>
	<main>
		<div class="container">
			<div class="box-atracciones">
				<div class="texto-atracciones">
					<h1>Conoce nuestro parque inspirado en el maravilloso mundo de
						J.R.R. Tolkien</h1>
					<p>¡Adéntrate en una increíble aventura dentro de la Tierra
						Media, conoce los personajes más emblemáticos de las películas,
						deléitate con las comidas más icónicas y diviértete en nuestros
						juegos pensados solo para los más audaces!</p>

				</div>


				<div class="slider-atracciones" href="atracciones.index">
					<div class="container-fluid">
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carouselExampleIndicators" data-slide-to="0"
									class="active"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
								<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item active"
									style="height: 400px; width: 500;">
									<img class="d-block w-100" src="assets/img/comarca.jpeg"
										alt="First slide">
								</div>
								<div class="carousel-item" style="height: 400px; width: 500;">
									<img class="d-block w-100"
										src="assets/img/parques-atracciones.jpg" alt="Second slide">
								</div>
								<div class="carousel-item" style="height: 400px; width: 500;">
									<img class="d-block w-100" src="assets/img/gandalf.jpg"
										alt="Third slide">
								</div>
								<div class="carousel-item" style="height: 400px; width: 500;">
									<img class="d-block w-100" src="assets/img/montania-rusa2.jpg"
										alt="Fourth slide">
								</div>
							</div>
							<a class="carousel-control-prev"
								href="#carouselExampleIndicators" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next"
								href="#carouselExampleIndicators" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
					</div>





				</div>
<a href="/TurismoTierraMedia/atracciones/index.do">
				<button type="button" class="btn btn-success" id="boton-atracciones">Saber más</button>
</a>

				<div class="promociones">
					<h4>Visitanos con toda tu familia o tu grupo de amigos con
						nuestros increibles paquetes de viajes y promociones.</h4>



					<img src="assets/img/montaña rusa.jpg" alt="">


				</div>
				<a href="/TurismoTierraMedia/promociones/index.do">
				<button type="button" class="btn btn-success">Saber más</button></a>
	</main>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</html>
