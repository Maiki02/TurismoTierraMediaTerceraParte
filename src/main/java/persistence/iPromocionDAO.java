package persistence;

import java.util.List;
import java.util.Map;

import model.producto.*;

public interface iPromocionDAO {

	public abstract List<Promocion> listarPromocionesValidas(Map<Integer, Atraccion> mapDeAtraccionesPorID);

	public Promocion find(Integer id, Map<Integer, Atraccion> mapDeAtraccionesPorID);
	
	public int actualizar(Promocion promocion);

}
