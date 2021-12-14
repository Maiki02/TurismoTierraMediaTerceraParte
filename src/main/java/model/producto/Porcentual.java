package model.producto;

import java.util.List;

import excepciones.AtraccionDeDistintoTipo;
import excepciones.ValorNegativo;

public class Porcentual extends Promocion {

	private double porcentajeDescuento;

	public Porcentual(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones,
			double porcentajeDescuento, int id, String descripcion) throws ValorNegativo, AtraccionDeDistintoTipo{
		super(nombre, tipoAtraccion, atracciones, id, descripcion);
		setPorcentajeDescuento(porcentajeDescuento);
		this.esValida=true;
	}

	/*
	 * @Pre: dado un valor que representa un porcentaje de descuento
	 * @Post: será asignado en caso de que este valor NO sea negativo.
	 */
	private void setPorcentajeDescuento(double porcentajeDescuento) throws ValorNegativo {
		ValorNegativo.verificarValor(porcentajeDescuento);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	// Getters:
	@Override
	public double getCosto() { // Representa el importe a pagar para comprar la promocion
		return super.getCosto() - this.calcularDescuento();
	}

	private double calcularDescuento() {
		return super.getCosto() * this.porcentajeDescuento / 100;
	}
	
	@Override
	public TipoDePromocion getTipoPromocion() {
		return TipoDePromocion.PORCENTUAL;
	}
	
	@Override
	public double getPremio() {
		return this.porcentajeDescuento;
	}
	
	

}