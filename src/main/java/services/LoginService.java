package services;

import java.util.LinkedList;
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
	
	public LoginService() {
		cargarAtraccionesYPromociones();
		userDao = DAOFactory.getUsuarioDAO();
	}
	
	
	public Usuario login(String username, String password) {
    	Usuario user = userDao.findByUsername(username, this.mapDeAtraccionesPorID, this.mapDePromocionesPorID);
    	if (user.isNull() || !user.verificarPassword(password)) {
    		user = NullUser.build();
    	}
    	return user;
	}
	
	public Usuario register(String username, String password, TipoDeAtraccion tipoFavorito, Double monedasDisponibles, Double horasDisponibles) {
    	Usuario usuario = userDao.findByUsername(username, this.mapDeAtraccionesPorID, this.mapDePromocionesPorID);
		
		if(usuario.isNull()) {
			//Integer id= userDao.obtenerUltimaIDUtilizada() + 1;
			usuario =new Usuario(-1, username, "", monedasDisponibles, horasDisponibles, tipoFavorito, 0.0, 0.0, new LinkedList<Producto>(), false);
			usuario.setPassword(password);
			userDao.insert(usuario);
			return usuario;
		}
		
		return NullUser.build();
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
