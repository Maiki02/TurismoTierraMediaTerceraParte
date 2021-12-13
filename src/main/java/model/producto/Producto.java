package model.producto;

import java.util.Iterator;

import excepciones.ValorNegativo;
import model.usuario.Usuario;

public abstract class Producto {
	protected String nombre;
	protected TipoDeAtraccion tipoAtraccion;
	protected double duracion;
	protected double costo;
	protected int id;
	protected String descripcion;
	protected boolean esValida;

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo, int id, String descripcion)
			throws ValorNegativo {
		this.id = id;
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		setDuracion(duracion);
		setCosto(costo);
		this.descripcion=descripcion;
		this.setEsValida(true); //Por defecto se crea un producto valido
	}

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, int id, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		this.descripcion=descripcion;
	}

	// Setters:
	public void setCosto(double costo) throws ValorNegativo {
		ValorNegativo.verificarValor(costo);
		this.costo = costo;
	}

	public void setDuracion(double duracion) throws ValorNegativo {
		ValorNegativo.verificarValor(duracion);
		this.duracion = duracion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoAtraccion(TipoDeAtraccion tipoAtraccion) {
		this.tipoAtraccion = tipoAtraccion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//---------------------------------

	// Getters: retornan sus atributos
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getCosto() {
		return this.costo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public TipoDeAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public Integer getID() {
		return this.id;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	// -------------------------------------------

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que la atraccion tenga cupos disponibles (caso
	 * contrario false)
	 */
	public boolean esPromocion() {
		return false;
	}

	public abstract boolean quedanCuposDisponibles();

	public abstract boolean ocuparAtraccion();

	@Override
	public String toString() {
		return nombre + " Tipo:" + this.tipoAtraccion + " Precio:" + getCosto() + " Horas:" + getDuracion();
	}

	public abstract boolean contiene(Producto producto);
	
	public boolean esProductoYaElecto(Usuario usuario) {
		Iterator<Producto> iter = usuario.getProductosComprados().iterator();
		boolean contiene = false;
		while (!contiene && iter.hasNext()) {
			contiene = this.contiene(iter.next());
		}
		return contiene;
	}

	public boolean esValida() {
		return esValida;
	}

	public void setEsValida(boolean esValida) {
		this.esValida = esValida;
	}

}
