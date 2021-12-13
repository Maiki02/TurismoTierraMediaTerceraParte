

var atraccionPremio = document.getElementById('atraccion-premio');
var porcentajeDescuento = document.getElementById('porcentaje-descuento');
var valorFinal = document.getElementById('precio-absoluto');

function aparecerAbsoluta() {
	valorFinal.setAttribute("type","number");
	porcentajeDescuento.setAttribute("type", "hidden");
	atraccionPremio.setAttribute("type", "hidden");
};

function aparecerAXB() {
	atraccionPremio.setAttribute("type","number");
	valorFinal.setAttribute("type", "hidden");
	porcentajeDescuento.setAttribute("type", "hidden");
};

function aparecerPorcentual() {
	porcentajeDescuento.setAttribute("type","number");
	valorFinal.setAttribute("type", "hidden");
	atraccionPremio.setAttribute("type", "hidden");
};
