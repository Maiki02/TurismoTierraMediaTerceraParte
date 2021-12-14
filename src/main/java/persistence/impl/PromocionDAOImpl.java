package persistence.impl;

import java.sql.*;
import java.util.*;

import excepciones.*;
import model.producto.*;
import persistence.iPromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements iPromocionDAO {

	private static final String SQL_LISTAR_VALIDAS = "SELECT id_promocion, nombre_promocion, tipo_promocion, tipo_atraccion, costo_promocion, descuento_promocion, id_atraccion_premio, promociones.descripcion "
			+ "FROM promociones "
			+ "LEFT JOIN tipo_promocion ON tipo_promocion.id_tipo_promocion = promociones.id_tipo_promocion "
			+ "LEFT JOIN atracciones ON atracciones.id_atraccion = promociones.id_atraccion_premio "
			+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = promociones.id_tipo_atraccion "
			+ "WHERE promociones.es_valida=1 ";
	private static final String SQL_FIND_ID = "SELECT id_promocion, nombre_promocion, tipo_promocion , tipo_atraccion, costo_promocion, promociones.descripcion "
			+ "FROM promociones "
			+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = promociones.id_tipo_atraccion "
			+ "LEFT JOIN atracciones ON atracciones.id_atraccion = promociones.id_atraccion_premio "
			+ "LEFT JOIN tipo_promocion ON tipo_promocion.id_tipo_promocion = promociones.id_tipo_promocion "
			+ "WHERE id_promocion=? and promociones.es_valida=1;";

	private static final String SQL_ACTUALIZAR_ABSOLUTA = "UPDATE promociones SET nombre_promocion=?, id_tipo_atraccion=?, descripcion=?, es_valida=?, costo_promocion=?, descuento_promocion=null, id_atraccion_premio=null, id_tipo_promocion=2 WHERE id_promocion=? ";
	private static final String SQL_ACTUALIZAR_PORCENTUAL = "UPDATE promociones SET nombre_promocion=?, id_tipo_atraccion=?, descripcion=?, es_valida=?, descuento_promocion=?, costo_promocion=null, id_atraccion_premio=null, id_tipo_promocion=1 WHERE id_promocion=? ";
	private static final String SQL_ACTUALIZAR_AXB = "UPDATE promociones SET nombre_promocion=?, id_tipo_atraccion=?, descripcion=?, es_valida=?, id_atraccion_premio=?, descuento_promocion=null, costo_promocion=null, id_tipo_promocion=3 WHERE id_promocion=? ";
	
	private static final String SQL_AGREGAR_ATRACCIONES_INVOLUCRADAS = "INSERT INTO atracciones_involucradas (id_promocion, id_atraccion) VALUES (?,?); ";
	private static final String SQL_ELIMINAR_ATRACCIONES_INVOLUCRADAS = "DELETE FROM atracciones_involucradas WHERE id_promocion = ?";
private static final String SQL_CREAR_ABSOLUTA = "INSERT INTO promociones (nombre_promocion, id_tipo_promocion, id_tipo_atraccion, descripcion, costo_promocion) VALUES (?,2,?,?,?); ";
private static final String SQL_CREAR_PORCENTUAL = "INSERT INTO promociones (nombre_promocion, id_tipo_promocion, id_tipo_atraccion, descripcion, descuento_promocion) VALUES (?,1,?,?,?);";
private static final String SQL_CREAR_AXB = "INSERT INTO promociones (nombre_promocion, id_tipo_promocion, id_tipo_atraccion, descripcion, id_atraccion_premio) VALUES (?,3,?,?,?);";

	
	
	public List<Promocion> listarPromocionesValidas(Map<Integer, Atraccion> mapDeAtraccionesPorID) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement instruccion = conn.prepareStatement(SQL_LISTAR_VALIDAS);
			ResultSet rs = instruccion.executeQuery();
			// ------------------------------------------------------
			List<Promocion> promociones = new ArrayList<Promocion>();
			Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion = crearMapDeAtraccionesInvolucradas(
					mapDeAtraccionesPorID);

			while (rs.next()) {
				try {
					Promocion nuevaPromocion = crearPromocion(rs, mapaDeIDPromocionAtraccion, mapDeAtraccionesPorID);
					promociones.add(nuevaPromocion);
				} catch (AtraccionDeDistintoTipo addt) {
					System.err.println(addt);
				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido");
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

			}

			return promociones;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private Promocion crearPromocion(ResultSet rs, Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion,
			Map<Integer, Atraccion> mapDeAtraccionesPorID)
			throws ValorNegativo, AtraccionDeDistintoTipo, NumberFormatException, IllegalArgumentException {
		try {
			// Tomamos el tipo de promocion
			TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(rs.getString("tipo_promocion"));

			// Tomamos los datos de la tabla
			int id = rs.getInt("id_promocion");
			String nombre = rs.getString("nombre_promocion");
			List<Atraccion> atraccionesInvolucradas = buscarAtraccionesInvolucradas(rs.getInt("id_promocion"),
					mapaDeIDPromocionAtraccion);
			TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));

			String descripcion = rs.getString("descripcion");

			Promocion nuevaPromocion = null;
			if (tipoPromocion == TipoDePromocion.ABSOLUTA) {
				Double costoPromocion = rs.getDouble("costo_promocion");
				nuevaPromocion = new Absoluta(nombre, tipoAtraccion, atraccionesInvolucradas, costoPromocion, id,
						descripcion);

			} else if (tipoPromocion == TipoDePromocion.PORCENTUAL) {
				Double descuentoPromocion = rs.getDouble("descuento_promocion");
				nuevaPromocion = new Porcentual(nombre, tipoAtraccion, atraccionesInvolucradas, descuentoPromocion, id,
						descripcion);

			} else if (tipoPromocion == TipoDePromocion.AXB) {
				int atraccionPremioID = rs.getInt("id_atraccion_premio");
				Atraccion atraccionPremio = mapDeAtraccionesPorID.get(Integer.valueOf(atraccionPremioID));
				atraccionesInvolucradas.add(atraccionPremio);
				nuevaPromocion = new AxB(nombre, tipoAtraccion, atraccionesInvolucradas, atraccionPremio, id,
						descripcion);
			}
			return nuevaPromocion;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private Map<Integer, List<Atraccion>> crearMapDeAtraccionesInvolucradas(Map<Integer, Atraccion> mapaDeAtracciones) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement instruccion = conn.prepareStatement("SELECT * FROM atracciones_involucradas");
			ResultSet rs = instruccion.executeQuery();
			// -----------------------------------------
			Map<Integer, List<Atraccion>> mapAtraccionesInvolucradas = new HashMap<Integer, List<Atraccion>>();

			while (rs.next()) {
				Integer promocionID = rs.getInt("id_promocion");
				Integer atraccionID = rs.getInt("id_atraccion");
				if (!mapAtraccionesInvolucradas.containsKey(promocionID)) {
					mapAtraccionesInvolucradas.put(promocionID, new LinkedList<Atraccion>());
				}
				mapAtraccionesInvolucradas.get(promocionID).add(mapaDeAtracciones.get(atraccionID));
			}
			return mapAtraccionesInvolucradas;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private List<Atraccion> buscarAtraccionesInvolucradas(int id, Map<Integer, List<Atraccion>> mapPromocionAtraccion) {
		return mapPromocionAtraccion.get(Integer.valueOf(id));
	}

	@Override
	public Promocion find(Integer id, Map<Integer, Atraccion> mapDeAtraccionesPorID) {
		Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion = crearMapDeAtraccionesInvolucradas(
				mapDeAtraccionesPorID);

		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(SQL_FIND_ID);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			return crearPromocion(resultados, mapaDeIDPromocionAtraccion, mapDeAtraccionesPorID);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	@Override
	public int actualizar(Promocion promocion) {
		try {
			Connection conn = null;
			PreparedStatement instruccion = null;
			conn = ConnectionProvider.getConnection();

			if (promocion.getTipoPromocion() == TipoDePromocion.ABSOLUTA) {
				instruccion = conn.prepareStatement(SQL_ACTUALIZAR_ABSOLUTA);

				instruccion.setDouble(5, promocion.getPremio());

			} else if (promocion.getTipoPromocion() == TipoDePromocion.AXB) {
				instruccion = conn.prepareStatement(SQL_ACTUALIZAR_AXB);

				instruccion.setInt(5, (int) promocion.getPremio());

			} else if (promocion.getTipoPromocion() == TipoDePromocion.PORCENTUAL) {
				instruccion = conn.prepareStatement(SQL_ACTUALIZAR_PORCENTUAL);

				instruccion.setDouble(5, promocion.getPremio());
			}

			instruccion.setString(1, promocion.getNombre());
			instruccion.setDouble(2, AtraccionDAOImpl.buscarID(promocion.getTipoAtraccion()));
			instruccion.setString(3, promocion.getDescripcion());
			instruccion.setBoolean(4, promocion.esValida());
			instruccion.setInt(6, promocion.getID());

			return instruccion.executeUpdate();// nos devuelve la cantidad de registros afectados
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public int actualizarListaDeAtraccionesInvolucradas(Promocion promocion, Atraccion atraccion) {

		try {
			Connection conn = null;
			PreparedStatement instruccion = null;
			conn = ConnectionProvider.getConnection();

			instruccion = conn.prepareStatement(SQL_AGREGAR_ATRACCIONES_INVOLUCRADAS);

			instruccion.setInt(1, promocion.getID());
			instruccion.setInt(2, atraccion.getID());

			return instruccion.executeUpdate();// nos devuelve la cantidad de registros afectados
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}
	
	
	
	@Override
	public Integer obtenerUltimaIDUtilizada() {
		
		try {
			String sql="SELECT seq FROM sqlite_sequence WHERE name='promociones'";
			Connection conn = null;
			PreparedStatement instruccion = null;
			conn = ConnectionProvider.getConnection();
			instruccion = conn.prepareStatement(sql);

			ResultSet rs = instruccion.executeQuery();

			
			while(rs.next()) {
				return rs.getInt("seq");
			}
			
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
		return null;
	}
	
	@Override
	public int borrarListaDeAtraccionesInvolucradas(Promocion promocion) {
		try {
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(SQL_ELIMINAR_ATRACCIONES_INVOLUCRADAS);
			statement.setInt(1, promocion.getID());
			return statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Promocion promocion) {
		try {
			Connection conn = null;
			PreparedStatement instruccion = null;
			conn = ConnectionProvider.getConnection();
			if (promocion.getTipoPromocion() == TipoDePromocion.ABSOLUTA) {
				instruccion = conn.prepareStatement(SQL_CREAR_ABSOLUTA);

				instruccion.setDouble(4, promocion.getPremio());
				System.out.println("Llega");
			} else if (promocion.getTipoPromocion() == TipoDePromocion.AXB) {
				instruccion = conn.prepareStatement(SQL_CREAR_AXB);

				instruccion.setInt(4, (int) promocion.getPremio());

			} else if (promocion.getTipoPromocion() == TipoDePromocion.PORCENTUAL) {
				instruccion = conn.prepareStatement(SQL_CREAR_PORCENTUAL);

				instruccion.setDouble(4, promocion.getPremio());
			}

			instruccion.setString(1, promocion.getNombre());
			instruccion.setDouble(2, AtraccionDAOImpl.buscarID(promocion.getTipoAtraccion()));
			instruccion.setString(3, promocion.getDescripcion());

			return instruccion.executeUpdate();// nos devuelve la cantidad de registros afectados
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

}
