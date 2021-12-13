package comparador;

import java.util.List;
import model.usuario.*;
import persistence.iUsuarioDAO;
import persistence.commons.DAOFactory;
public class main {

	public static void main(String[] args) {
		iUsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		List<Usuario> usuarios = userDAO.listarUsuarios(null, null);
		for(Usuario usuario: usuarios) {
			System.out.println(usuario);
			usuario.setPassword("1234");
			userDAO.actualizarContraseña(usuario);
		}
		System.out.println("Listo");

	}

}
