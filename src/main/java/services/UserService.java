package services;

import java.util.List;
import java.util.Map;

import org.w3c.dom.UserDataHandler;

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

	public void delete(Integer id) {
		Usuario usuario = usuarioDAO.find(id, this.mapDeAtraccionesPorID, this.mapDePromocionesPorID);
		
		
		//En caso de eliminar un usuario aumentamos los cupos de las atracciones que había comprado
		/*for(Producto producto: usuario.getProductosComprados()) {
			producto.aumentarUnCupo();
			if(producto.esPromocion()) {
				Promocion promocion= (Promocion) producto;
				for(Atraccion atraccion: promocion.getAtracciones()) {
					atraccionDAO.actualizar(atraccion); //Actualizamos los cupos de cada atraccion involucrada
				}
			} else {
				atraccionDAO.actualizar((Atraccion) producto); //Actualizamos los cupos de la atraccion
			}
		}*/
		
		
		usuario.setEsValido(false); // seteamos al usuario en invalido
		usuarioDAO.actualizar(usuario); // Lo actualizamos. 
		// al actualizar su atributo es_valido en 0, no se mostrará mas
		
	}

	public Usuario update(Integer id, String nombre, Double monedasDisponibles, Double tiempo, Double totalAPagar,
			Double totalHorasGastadas, TipoDeAtraccion tipoAtraccion, boolean esAdmin) {
		Usuario usuario= this.find(id);
		usuario.setNombre(nombre);
		usuario.setMonedasDisponibles(monedasDisponibles);
		usuario.setHorasDisponibles(tiempo);
		usuario.setTotalAPagar(totalAPagar);
		usuario.setTotalHorasGastadas(totalHorasGastadas);
		usuario.setTipoFavorito(tipoAtraccion);
		usuario.setEsValido(true);
		usuario.setEsAdmin(esAdmin);
		usuarioDAO.actualizar(usuario);
		
		return usuario;
	}

}
