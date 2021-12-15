package persistence;

import java.util.*;

import model.producto.*;
import model.usuario.*;

public interface iUsuarioDAO  {
    
	public List<Usuario> listarUsuarios(Map<Integer, Atraccion> mapDeAtraccionesPorID, 
			Map<Integer, Promocion> mapDePromocionesPorID);

	public int insert(Usuario usuario);
	public int actualizarContraseña(Usuario usuario);
	public int actualizar(Usuario usuario);
	
	public Usuario find(Integer id, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID);
	
	public Usuario findByUsername(String username, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID);
	public int agregarProductoAlItinerario(Integer id, Producto producto);

}