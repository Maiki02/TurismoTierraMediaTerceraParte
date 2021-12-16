package model.usuario;

import java.util.List;

import excepciones.ValorNegativo;
import model.producto.*;
import utils.Crypt;

public class Usuario {

	private int id;
	private String nombre, password;
	private double monedasDisponibles, horasDisponibles;
	private TipoDeAtraccion tipoFavorito;
	private double totalAPagar, totalHorasGastadas; // Horas a jugar
	private List<Producto> productosComprados;
	private boolean esAdmin;
	private boolean esValido;

	public Usuario(int idUsuario, String nombre, String password, double monedasDisponibles, double horasDisponibles,
			TipoDeAtraccion tipoFavorito, double totalAPagar, double totalHorasGastadas,
			List<Producto> productosComprados, boolean esAdmin) {
		this.id = idUsuario;
		this.nombre = nombre;
		this.password = password;
		setHorasDisponibles(horasDisponibles);
		setMonedasDisponibles(monedasDisponibles);
		this.tipoFavorito = tipoFavorito;
		this.totalAPagar = totalAPagar;
		this.totalHorasGastadas = totalHorasGastadas;
		this.productosComprados = productosComprados;
		this.esAdmin = esAdmin;
	}

	// Setters:
	public void setMonedasDisponibles(double presupuesto) throws ValorNegativo {
		ValorNegativo.verificarValor(presupuesto);
		this.monedasDisponibles = presupuesto;

	}

	public void setHorasDisponibles(double horasDisponibles) throws ValorNegativo {

		ValorNegativo.verificarValor(horasDisponibles);
		this.horasDisponibles = horasDisponibles;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTotalHorasGastadas() {
		return totalHorasGastadas;
	}

	public void setTotalHorasGastadas(double totalHorasGastadas) {
		this.totalHorasGastadas = totalHorasGastadas;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public void setTipoFavorito(TipoDeAtraccion tipoFavorito) {
		this.tipoFavorito = tipoFavorito;
	}

	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	// Getters:
	public double getHorasDisponibles() {
		return horasDisponibles;
	}

	public double getMonedasDisponibles() {
		return monedasDisponibles;
	}

	public double getTotalAPagar() {
		return totalAPagar;
	}

	public TipoDeAtraccion getTipoFavorito() {
		return tipoFavorito;
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public double getHorasGastadas() {
		return totalHorasGastadas;
	}

	public int getID() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}
	// -----------------------------------------------------

	private void descontarMonedas(Producto producto) {
		// No verificamos si el importe a pagar es mayor a sus monedas disponibles
		// porque solo la invocamos si se cumple el puedeComprar
		this.monedasDisponibles -= producto.getCosto();
		this.totalAPagar += producto.getCosto();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: descuenta la cantidad de horas necesarias del producto
	 */
	private void descontarHorasDisponibles(Producto producto) {
		horasDisponibles -= producto.getDuracion();
		totalHorasGastadas += producto.getDuracion();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que el usuario cuente con monedas disponibles
	 */

	public boolean leAlcanzanLasMonedas(Producto producto) {
		return this.monedasDisponibles >= producto.getCosto();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que el usuario cuente con horas disponibles
	 */

	public boolean leAlcanzanLasHoras(Producto producto) {
		return this.horasDisponibles >= producto.getDuracion();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que el usuario cuente con monedas,horas y
	 * cupos disponibles
	 */
	public boolean puedeComprar(Producto producto) {
		return leAlcanzanLasMonedas(producto) && leAlcanzanLasHoras(producto) && producto.quedanCuposDisponibles();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: Si puede comprar lo agregaa a la lista de productos comprados
	 */
	public void comprarProducto(Producto producto) {

		if (puedeComprar(producto)) {
			descontarMonedas(producto);
			descontarHorasDisponibles(producto);

			productosComprados.add(producto); // Lo agregamos a lista de productos comprados
			producto.ocuparAtraccion();
		}
	}

	public boolean verificarPassword(String password) {
		// this.password en realidad es el hash del password
		// return true;
		return Crypt.match(password, this.password);
	}

	public boolean isNull() {
		return false;
	}

	public boolean esAdmin() {
		return this.esAdmin;
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + " Tipo favorito: " + this.tipoFavorito + "\n Monedas: " + this.monedasDisponibles
				+ " Horas: " + this.horasDisponibles;
	}

	public boolean esValido() {
		return esValido;
	}

	public void setEsValido(boolean esValido) {
		this.esValido = esValido;
	}

}
