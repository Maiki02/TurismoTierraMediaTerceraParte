
var atraccionPremio = document.getElementById('atraccion-premio');
var porcentajeDescuento = document.getElementById('porcentaje-descuento');
var valorFinal = document.getElementById('precio-absoluto');
var selectTipoAtraccion = document.getElementsByClassName('seleccion-tipo-atraccion');

var atraccionesInvolucradas = document.getElementsByClassName('atracciones-involucradas');
var tipoPromocion = document.getElementById('tipo-promocion');
var atraccionesInvolucradasElectas = document.getElementById('atracciones-involucradas-electas');
var checkboxAtraccion = document.getElementsByClassName('checkbox-atraccion');


function aparecerAbsoluta() {
	tipoPromocion.value = "ABSOLUTA";
	valorFinal.setAttribute("type", "number");
	porcentajeDescuento.setAttribute("type", "hidden");
	atraccionPremio.style.visibility = "hidden";
	atraccionPremio.style.display = "none";
};

function aparecerAXB(numAcordeon) {
	ponerAtraccionesEnAXB(numAcordeon);
	tipoPromocion.value = "AXB";
	atraccionPremio.style.visibility = "visible";
	atraccionPremio.style.display = "block";
	valorFinal.setAttribute("type", "hidden");
	porcentajeDescuento.setAttribute("type", "hidden");

};

function aparecerPorcentual() {
	tipoPromocion.value = "PORCENTUAL";
	porcentajeDescuento.setAttribute("type", "number");
	valorFinal.setAttribute("type", "hidden");
	atraccionPremio.style.visibility = "hidden";
	atraccionPremio.style.display = "none";

};

function ocultarAtraccionesInvolucradas() {
	for (let i = 0; i < atraccionesInvolucradas.length; i++) {
		atraccionesInvolucradas[i].style.visibility = "hidden";
		atraccionesInvolucradas[i].style.display = "none";
	}
}


function aparecerAtraccionesInvolucradas(numAcordeon) {

	ocultarAtraccionesInvolucradas();

	if (selectTipoAtraccion[numAcordeon].value == "PAISAJE") {
		ponerAtraccionesDe("PAISAJE", numAcordeon);
	} else if (selectTipoAtraccion[numAcordeon].value == "AVENTURA") {
		ponerAtraccionesDe("AVENTURA", numAcordeon);
	} else if (selectTipoAtraccion[numAcordeon].value == "DEGUSTACION") {
		ponerAtraccionesDe("DEGUSTACION", numAcordeon);
	}
}

function ponerAtraccionesDe(tipoAtraccion, numAcordeon) {
	let cantAtracciones = atraccionesInvolucradas.length;
	var atraccionesValidas = document.getElementsByClassName('atracciones-validas');
	atraccionesValidas[numAcordeon].textContent = "";

	for (let i = 0; i < cantAtracciones; i++) {
		let atraccionYTipo = atraccionesInvolucradas[i].textContent.split(",");
		if (atraccionYTipo[1] == tipoAtraccion) {
			atraccionesValidas[numAcordeon].insertAdjacentHTML('afterbegin',
				'<input type="checkbox" class="checkbox-atraccion" value="' + atraccionYTipo[0] + '" onclick="registrarAtraccionElecta()" >' + atraccionYTipo[0] + '<br>');
		}
		
	}
}

function ponerAtraccionesEnAXB(numAcordeon) {
	atraccionPremio.innerHTML = "";
	let cantAtracciones = atraccionesInvolucradas.length;
	let tipoAtraccion = selectTipoAtraccion[numAcordeon].value;
	for (let i = 0; i < cantAtracciones; i++) {
		let atraccionYTipo = atraccionesInvolucradas[i].textContent.split(",");
		if (atraccionYTipo[1] == tipoAtraccion) {
			atraccionPremio.insertAdjacentHTML('afterbegin', "<option value='" + atraccionYTipo[0] + "'>" + atraccionYTipo[0] + "</option>");
		}
	}

}

function registrarAtraccionElecta() {
	let cantidadAtraccionesPosibles = checkboxAtraccion.length;
	let salidaFinal="";
	for (let i = 0; i < cantidadAtraccionesPosibles; i++) {
		if (checkboxAtraccion[i].checked) {
			salidaFinal += checkboxAtraccion[i].value + "/";
		}
	}

	atraccionesInvolucradasElectas.value = salidaFinal;
}

for(let i=0; i<selectTipoAtraccion.length; i++){
	aparecerAtraccionesInvolucradas(i);
}

atraccionPremio.style.visibility = "hidden";
atraccionPremio.style.display = "none";
tipoPromocion.style.display = "none";
tipoPromocion.style.visibility = "hidden";