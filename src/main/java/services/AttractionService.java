package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.producto.*;
import model.usuario.Usuario;
import persistence.iAtraccionDAO;
import persistence.iPromocionDAO;
import persistence.iUsuarioDAO;
import persistence.commons.DAOFactory;

public class AttractionService {
	iAtraccionDAO atraccionDAO;
	iUsuarioDAO usuarioDAO;

	public AttractionService() {
		this.atraccionDAO = DAOFactory.getAtraccionDAO();
	}

	public List<Atraccion> list(int esValida) {
		return DAOFactory.getAtraccionDAO().listar(esValida);
	}

	public Atraccion create(String nombre, Integer costo, Double duracion, Integer capacidad,
			TipoDeAtraccion tipoAtraccion, String descripcion) {

		Atraccion atraccion = new Atraccion(nombre, costo, duracion, capacidad, tipoAtraccion, -1, descripcion); // Faltan

		if (atraccionDAO.existeLaAtraccion(atraccion)) {
			atraccion.getErrors().put("flash", "Atraccion ya ingresada");
		} else {
			atraccionDAO.insert(atraccion);
		}

		return atraccion;

	}

	public Atraccion update(Integer id, String nombre, Double costo, Double duracion, Integer capacidad,
			TipoDeAtraccion tipoAtraccion, String descripcion) {
		Atraccion atraccion = atraccionDAO.find(id);
		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);
		atraccion.setCupo(capacidad);
		atraccion.setTipoAtraccion(tipoAtraccion);
		atraccion.setDescripcion(descripcion);

		if (atraccion.isValid()) {
			atraccionDAO.actualizar(atraccion);
		}

		return atraccion;
	}

	public void delete(Integer id) {
		Atraccion atraccion = atraccionDAO.find(id);
		atraccion.setEsValida(false); // seteamos la atraccion en invalida
		atraccionDAO.actualizar(atraccion); // La actualizamos. Como siempre elegimos la opcion 1 (listar atracciones
											// validas),
		// al actualizar su atributo es_valida en 0, no se mostrará mas
	}

	public Atraccion find(Integer id) {
		return atraccionDAO.find(id);
	}

	public Map<String, String> buy(Usuario usuario, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();
		usuarioDAO = DAOFactory.getUsuarioDAO();

		Atraccion atraccion = atraccionDAO.find(attractionId);

		if (!atraccion.quedanCuposDisponibles()) {
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!usuario.leAlcanzanLasMonedas(atraccion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!usuario.leAlcanzanLasHoras(atraccion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			usuario.comprarProducto(atraccion); // Compramos el producto

			atraccionDAO.actualizar(atraccion); // Actualizamos los cupos de la atraccion

			usuarioDAO.actualizar(usuario); // Actualizamos la base de datos

			usuarioDAO.agregarProductoAlItinerario(usuario.getID(), atraccion);

		}

		return errors;
	}

}
