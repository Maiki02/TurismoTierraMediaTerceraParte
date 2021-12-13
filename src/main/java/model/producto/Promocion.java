package model.producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.AtraccionDeDistintoTipo;

public abstract class Promocion extends Producto {
	List<Atraccion> atracciones;

	public Promocion(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, int id, String descripcion)
			throws AtraccionDeDistintoTipo {
		super(nombre, tipoAtraccion, id, descripcion);
		setAtracciones(atracciones, tipoAtraccion);
		this.costo = calcularCosto();
		this.duracion = calcularDuracion();
	}

	/*
	 * @Pre: dada una lista de atracciones y un TipoDeAtraccion
	 * 
	 * @Post: asigna la lista de atracciones a su atributo atracciones, verificando
	 * si todas las atracciones tienen el mismo TipoDeAtraccion
	 */
	private void setAtracciones(List<Atraccion> atracciones, TipoDeAtraccion tipoAtraccion)
			throws AtraccionDeDistintoTipo {
		if (!sonAtraccionesValidas(atracciones, tipoAtraccion)) { // Si no son atracciones validas, lanza excepcion
			throw new AtraccionDeDistintoTipo("Hay atracciones que no son del tipo: " + tipoAtraccion);
		}
		this.atracciones = atracciones;
	}

	private boolean sonAtraccionesValidas(List<Atraccion> atracciones, TipoDeAtraccion tipoAtraccion) {
		for (Atraccion atraccion : atracciones) {
			if (!esAtraccionValida(atraccion, tipoAtraccion)) // Si hay una atraccion invalida devuelve false
				return false;
		}
		return true;
	}

	private boolean esAtraccionValida(Atraccion atraccion, TipoDeAtraccion tipoAtraccion) {
		return atraccion.getTipoAtraccion() == tipoAtraccion;
	}

	// Getters:
	public List<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	// ----------------------------------------

	// Calcular duracion y costo:
	public double calcularCosto() {
		double costo = 0;
		for (Atraccion atraccion : atracciones)
			costo += atraccion.getCosto();

		return costo;
	}

	public double calcularDuracion() {
		double duracion = 0;
		for (Atraccion atraccion : atracciones)
			duracion += atraccion.getDuracion();

		return duracion;
	}

	// ---------------------------------------

	public static Map<Integer, Promocion> crearMapDePromociones(List<Promocion> promociones) {
		Map<Integer, Promocion> mapaDePromociones = new HashMap<Integer, Promocion>();
		for (Promocion promocion : promociones) {
			mapaDePromociones.put(promocion.getID(), promocion);
		}
		return mapaDePromociones;
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	public boolean quedanCuposDisponibles() {
		for (Atraccion atraccion : atracciones) {
			if (!atraccion.quedanCuposDisponibles())
				return false;
		}
		return true;
	}

	public boolean ocuparAtraccion() {
		if (this.quedanCuposDisponibles()) {
			for (Atraccion atraccion : atracciones) {
				atraccion.ocuparAtraccion();
			}
			return true;
		}
		return false;
	}

	public TipoDePromocion getTipoPromocion() {
		return null;
	}
	
	@Override
	public boolean contiene(Producto producto) {

		for (Atraccion atraccion : this.atracciones) {
			if (atraccion.contiene(producto)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "Promocion:" + super.toString();
	}

	public abstract double getPremio();

}
