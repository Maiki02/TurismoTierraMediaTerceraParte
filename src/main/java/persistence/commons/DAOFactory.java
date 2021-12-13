package persistence.commons;

import persistence.iAtraccionDAO;
import persistence.iPromocionDAO;
import persistence.iUsuarioDAO;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {

	public static iUsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static iPromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	public static iAtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	
}
