package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.producto.*;
import model.usuario.Usuario;
import persistence.iAtraccionDAO;
import persistence.iUsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	iAtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
	iUsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
	Map<Integer, Atraccion> mapDeAtraccionesPorID;
	Map<Integer, Promocion> mapDePromocionesPorID;

	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();
		cargarAtracciones();

		Usuario user = userDAO.find(userId, mapDeAtraccionesPorID, mapDePromocionesPorID);
		Atraccion attraction = attractionDAO.find(attractionId);

		if (!attraction.quedanCuposDisponibles()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.leAlcanzanLasMonedas(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.leAlcanzanLasHoras(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.comprarProducto(attraction); //Compramos el producto
			attractionDAO.actualizar(attraction); 
			userDAO.actualizar(user); //Actualizamos la base de datos
		}

		return errors;

	}
	
	private void cargarAtracciones() {
		List<Atraccion> atracciones = this.attractionDAO.listar(1);
		this.mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);
	}

}
