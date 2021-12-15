package services;

import java.util.List;
import java.util.Map;

import model.nullobjects.NullUser;
import model.producto.*;
import model.usuario.Usuario;
import persistence.*;
import persistence.commons.DAOFactory;

public class UserService {
	private iAtraccionDAO atraccionDAO;
	private iPromocionDAO promocionDAO;
	private iUsuarioDAO usuarioDAO;
	Map<Integer, Atraccion> mapDeAtraccionesPorID;
	Map<Integer, Promocion> mapDePromocionesPorID;
	
	public UserService() {
		promocionDAO = DAOFactory.getPromocionDAO();
		atraccionDAO = DAOFactory.getAtraccionDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		
		List<Atraccion> atracciones = atraccionDAO.listar(1); //Listamos las atracciones válidas
		mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);
		List<Promocion> promociones = promocionDAO.listarPromocionesValidas(mapDeAtraccionesPorID);
		mapDePromocionesPorID = Promocion.crearMapDePromociones(promociones);
	}
	
	public List<Usuario> list() {
		
		return usuarioDAO.listarUsuarios(mapDeAtraccionesPorID, mapDePromocionesPorID);
	}

	public Usuario find(Integer id) {
		Usuario user=usuarioDAO.find(id, this.mapDeAtraccionesPorID, this.mapDePromocionesPorID);
		
		if (user.isNull()) {
    		user = NullUser.build();
    	}
		return user;
	}

}
