package persistence;

import java.util.List;
import java.util.Map;

import model.producto.*;

public interface iPromocionDAO {

	public abstract List<Promocion> listarPromocionesValidas(Map<Integer, Atraccion> mapDeAtraccionesPorID);

	public Promocion find(Integer id, Map<Integer, Atraccion> mapDeAtraccionesPorID);
	
	public int actualizar(Promocion promocion);

	public abstract int actualizarListaDeAtraccionesInvolucradas(Promocion promocion, Atraccion atraccion);

	int borrarListaDeAtraccionesInvolucradas(Promocion promocion);

	public abstract int insert(Promocion promocion);

	Integer obtenerUltimaIDUtilizada();

}
