package services;

import java.util.List;
import java.util.Map;
import model.nullobjects.*;
import model.producto.*;
import model.usuario.Usuario;
import persistence.iAtraccionDAO;
import persistence.iPromocionDAO;
import persistence.iUsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {
	iPromocionDAO promocionDAO;
	iAtraccionDAO atraccionDAO;
	iUsuarioDAO userDao;
	Map<Integer, Atraccion> mapDeAtraccionesPorID;
	Map<Integer, Promocion> mapDePromocionesPorID;
	
	public Usuario login(String username, String password) {
		cargarAtraccionesYPromociones();
		userDao = DAOFactory.getUsuarioDAO();
    	Usuario user = userDao.findByUsername(username, this.mapDeAtraccionesPorID, this.mapDePromocionesPorID);
    	if (user.isNull() || !user.verificarPassword(password)) {
    		user = NullUser.build();
    	}
    	return user;
	}
	
	private void cargarAtraccionesYPromociones() {
		promocionDAO = DAOFactory.getPromocionDAO();
		atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.listar(1);
		this.mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);

		List<Promocion> promociones = promocionDAO.listarPromocionesValidas(mapDeAtraccionesPorID);
		this.mapDePromocionesPorID = Promocion.crearMapDePromociones(promociones);
	}
	
}
