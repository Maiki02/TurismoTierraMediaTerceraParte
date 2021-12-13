package services;

import java.util.ArrayList;
import java.util.List;

import model.producto.*;
import model.usuario.Usuario;
import persistence.iAtraccionDAO;
import persistence.commons.DAOFactory;

public class TypeAttractionService {

	public List<String> list() {
		TipoDeAtraccion[] tipoDeAtraccion= TipoDeAtraccion.values();
		List<String> tipoDeAtracciones= new ArrayList<String>();
		
		for(TipoDeAtraccion tipoAtraccion: tipoDeAtraccion) {
			tipoDeAtracciones.add(tipoAtraccion.toString());
		}
		return tipoDeAtracciones;
	}

	

}
