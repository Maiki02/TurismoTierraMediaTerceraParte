package model.producto;

import java.util.List;

import excepciones.AtraccionDeDistintoTipo;
import persistence.commons.DAOFactory;

public class AxB extends Promocion {

	private Atraccion premio;

	public AxB(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, 
			Atraccion premio, int id, String descripcion) throws AtraccionDeDistintoTipo {
		super(nombre, tipoAtraccion, atracciones, id, descripcion);
		this.premio = premio; //El premio está contenido en la lista de atracciones
		//Por lo tanto si es de distinto tipo, será verificado en su padre.
		
		this.esValida=true;
	}

	@Override
	public double getCosto() {
		return super.getCosto() - premio.getCosto();
	}
	
	@Override
	public TipoDePromocion getTipoPromocion() {
		return TipoDePromocion.AXB;
	}
	
	
	//Obtiene la id de la atraccion premio
	public double getPremio() {
		return this.premio.getID();
	}
	
	
}