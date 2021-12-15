let direccionImagen = "../assets/img/atracciones/";

function imageExists(nombreImagen) {
	let direccion = "../img/atracciones/" + nombreImagen + ".jpg";
	var http = new XMLHttpRequest();

	http.open('HEAD', direccion, false);
	http.send();

	return http.status != 404;

}

function ponerImagen(nombreImagen, id) {
	let imagen = document.getElementById("nro-atraccion-" + id);
	if (imageExists(nombreImagen)) {
		imagen.setAttribute("src", "../assets/img/atracciones/" + nombreImagen + ".jpg");
	} else {
		imagen.setAttribute("src", "../assets/img/atracciones/Default.jpg");
	}
}