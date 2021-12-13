package services;

import java.util.List;

import model.producto.*;
import persistence.iAtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Atraccion> list(int esValida) {
		return DAOFactory.getAtraccionDAO().listar(esValida);
	}

	public Atraccion create(String nombre, Integer costo, Double duracion, Integer capacidad,
			TipoDeAtraccion tipoAtraccion, String descripcion) {

		Atraccion atraccion = new Atraccion(nombre, costo, duracion, capacidad, tipoAtraccion, -1, descripcion); // Faltan
		
		iAtraccionDAO atraccionDAO= DAOFactory.getAtraccionDAO();
		if(atraccionDAO.existeLaAtraccion(atraccion)) {
			atraccion.getErrors().put("flash", "Atraccion ya ingresada");
		} else {
			atraccionDAO.insert(atraccion);
		}
		
		return atraccion;

	}

	public Atraccion update(Integer id, String nombre, Double costo, Double duracion, Integer capacidad, TipoDeAtraccion tipoAtraccion, String descripcion) {
		iAtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = attractionDAO.find(id);
		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);
		atraccion.setCupo(capacidad);
		atraccion.setTipoAtraccion(tipoAtraccion);
		atraccion.setDescripcion(descripcion);
	

		if (atraccion.isValid()) {
			attractionDAO.actualizar(atraccion);
		}

		return atraccion;
	}

	public void delete(Integer id) {
		iAtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion= attractionDAO.find(id);
		atraccion.setEsValida(false); //seteamos la atraccion en invalida
		attractionDAO.actualizar(atraccion); //La actualizamos. Como siempre elegimos la opcion 1 (listar atracciones validas),
		//al actualizar su atributo es_valida en 0, no se mostrará mas
	}

	public Atraccion find(Integer id) {
		iAtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		return attractionDAO.find(id);
	}

}
