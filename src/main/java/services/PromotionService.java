package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
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

	public void create(Promocion promocion) {
		promocionDAO.insert(promocion);
		System.out.println("Isertada en la base de datos");
		for(Atraccion atraccion: promocion.getAtracciones()) { //Actualizamos la lista de atracciones_involucradas
			promocionDAO.actualizarListaDeAtraccionesInvolucradas(promocion, atraccion); //Agregamos una nueva
		}
		
	}

	public void update(Promocion promocion) {
		promocionDAO.actualizar(promocion);
		promocionDAO.borrarListaDeAtraccionesInvolucradas(promocion); //Borramos la lista de atracciones involucradas antigua
		for(Atraccion atraccion: promocion.getAtracciones()) {
			promocionDAO.actualizarListaDeAtraccionesInvolucradas(promocion, atraccion); //Agregamos una nueva
		}
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

	public List<Atraccion> buscarAtraccionesInvolucradas(String atracciones) {
		atracciones=atracciones.substring(0,atracciones.length());
		String[] atraccionesInvolucradas = atracciones.split("/");
		List<Atraccion> listAtraccionesInvolucradas = new ArrayList<Atraccion>();

		for (String atraccion : atraccionesInvolucradas) {
			listAtraccionesInvolucradas.add(buscarAtraccion(atraccion));
		}

		return listAtraccionesInvolucradas;

	}

	public Atraccion buscarAtraccion(String nombre) {
		return atraccionDAO.findByAttractionName(nombre);
	}
	

	public Promocion crearPromocion(HttpServletRequest req, Integer id) {
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(req.getParameter("tipo-atraccion"));
		TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(req.getParameter("tipo-promocion"));
		List<Atraccion> atraccionesInvolucradas = this
				.buscarAtraccionesInvolucradas(req.getParameter("atracciones-involucradas-electas"));

		Promocion promocion = null;

		if (tipoPromocion == TipoDePromocion.AXB) {
			Atraccion atraccion = this.buscarAtraccion(req.getParameter("atraccion-premio"));
			promocion = new AxB(nombre, tipoAtraccion, atraccionesInvolucradas, atraccion, id, descripcion);

		} else if (tipoPromocion == TipoDePromocion.ABSOLUTA) {
			Double costo = Double.parseDouble(req.getParameter("precio-absoluto"));
			promocion = new Absoluta(nombre, tipoAtraccion, atraccionesInvolucradas, costo, id, descripcion);


		} else if (tipoPromocion == TipoDePromocion.PORCENTUAL) {
			Double descuento = Double.parseDouble(req.getParameter("porcentaje-descuento"));
			promocion = new Porcentual(nombre, tipoAtraccion, atraccionesInvolucradas, descuento, id, descripcion);

		}
		return promocion;
	}

	public Integer establecerID() {
		return promocionDAO.obtenerUltimaIDUtilizada() + 1;
		
	}

}

