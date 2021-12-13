package services;

import java.util.List;
import java.util.Map;

import model.producto.*;
import persistence.iAtraccionDAO;
import persistence.iPromocionDAO;
import persistence.commons.DAOFactory;

public class PromotionService {
	private iAtraccionDAO atraccionDAO;
	private iPromocionDAO promocionDAO;
	Map<Integer, Atraccion> mapDeAtraccionesPorID;

	public PromotionService() {
		atraccionDAO = DAOFactory.getAtraccionDAO();
		promocionDAO = DAOFactory.getPromocionDAO();
		List<Atraccion> atracciones = atraccionDAO.listar(1); // Listamos las atracciones válidas
		mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);
	}

	public List<Promocion> list() {
		return promocionDAO.listarPromocionesValidas(this.mapDeAtraccionesPorID);
	}

	public Promocion create(String nombre, Integer costo, Double duracion, Integer capacidad) {
		return null;

	}

	public Atraccion update(Integer id, String nombre, Double costo, Double duracion, Integer capacidad,
			TipoDeAtraccion tipoAtraccion, String descripcion) {
		return null;
	}

	public void delete(Integer id) {
		Promocion promocion = promocionDAO.find(id, mapDeAtraccionesPorID);

		promocion.setEsValida(false); // seteamos la atraccion en invalida
		promocionDAO.actualizar(promocion); // La actualizamos. Como siempre elegimos la opcion 1 (listar atracciones
											// validas),
		// al actualizar su atributo es_valida en 0, no se mostrará mas

	}

	public Atraccion find(Integer id) {
		return null;
	}

}
