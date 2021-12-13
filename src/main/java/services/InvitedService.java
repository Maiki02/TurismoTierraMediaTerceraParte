package services;

import model.nullobjects.NullUser;
import model.producto.TipoDeAtraccion;
import model.usuario.Usuario;
import persistence.iUsuarioDAO;
import persistence.commons.DAOFactory;

public class InvitedService {
	final static String NOMBRE_USUARIO="invitado";
	
	public Usuario login() {
		iUsuarioDAO userDao = DAOFactory.getUsuarioDAO();
    	Usuario usuario = userDao.findByUsername(NOMBRE_USUARIO, null, null);
		
    	if (usuario.isNull()) {
    		usuario = NullUser.build();
    	}
    	return usuario;
	}
}
