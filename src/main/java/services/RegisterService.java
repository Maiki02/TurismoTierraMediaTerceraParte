package services;

import model.nullobjects.NullUser;
import model.producto.TipoDeAtraccion;
import model.usuario.Usuario;
import persistence.iUsuarioDAO;
import persistence.commons.DAOFactory;

public class RegisterService {

	
	public Usuario register(String username, String password, TipoDeAtraccion tipoFavorito, Double monedasDisponibles, Double horasDisponibles) {
		iUsuarioDAO userDao = DAOFactory.getUsuarioDAO();
    	Usuario usuario = userDao.findByUsername(username, null, null);
		
		if(usuario.isNull()) {
			usuario =new Usuario(-1, username, "", monedasDisponibles, horasDisponibles, tipoFavorito, 0.0, 0.0, null, false);
			usuario.setPassword(password);
			DAOFactory.getUsuarioDAO().insert(usuario);
			return usuario;
		}
		
		return NullUser.build();
	}
}
