package persistence;

import java.util.List;

import model.producto.Atraccion;


public interface iAtraccionDAO {
	public List<Atraccion> listar(int esValida);

	public int actualizar(Atraccion atraccion);

	int insert(Atraccion atraccion);
	
	public boolean existeLaAtraccion(Atraccion atraccion);
	public Atraccion find(Integer id);
	public int delete(Atraccion atraccion);

}
