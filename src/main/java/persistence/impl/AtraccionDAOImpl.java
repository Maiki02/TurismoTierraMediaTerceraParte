package persistence.impl;

import java.sql.*;
import java.util.*;

import excepciones.*;
import model.nullobjects.NullUser;
import model.producto.*;
import model.usuario.Usuario;
import persistence.iAtraccionDAO;
import persistence.iUsuarioDAO;
import persistence.commons.*;

public class AtraccionDAOImpl implements iAtraccionDAO {
	private static final String SQL_LISTAR = "SELECT id_atraccion, nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion, descripcion "
			+ "FROM atracciones JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = atracciones.id_tipo_atraccion "
			+ "WHERE es_valida=";

	private static final String SQL_ACTUALIZAR = "UPDATE atracciones SET nombre_atraccion=?, tiempo_atraccion=?, costo_atraccion=?, cupo=?, id_tipo_atraccion=?, descripcion=?, es_valida=? WHERE id_atraccion = ?";

	@Override
	public List<Atraccion> listar(int esValida) {
		try {
			List<Atraccion> atracciones = new ArrayList<Atraccion>();

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement instruccion = conn.prepareStatement(SQL_LISTAR + esValida);
			ResultSet rs = instruccion.executeQuery();
			int id_atraccion = 0; // La declaramos acá para mostrarla en el catch

			while (rs.next()) {
				try {

					Atraccion atraccion = toAtraccion(rs);
						atracciones.add(atraccion);

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage() + " en la id: " + id_atraccion);
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido en la id: " + id_atraccion);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida en la id: " + id_atraccion);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			return atracciones;

		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO atracciones (nombre_atraccion, tiempo_atraccion, costo_atraccion, cupo, id_tipo_atraccion, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, atraccion.getNombre());
			statement.setDouble(i++, atraccion.getDuracion());
			statement.setDouble(i++, atraccion.getCosto());
			statement.setInt(i++, atraccion.getCuposDisponibles());
			statement.setInt(i++, buscarID(atraccion.getTipoAtraccion()));
			statement.setString(i++, atraccion.getDescripcion());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM atracciones WHERE id_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion find(Integer id) {
		try {
			String sql = "SELECT id_atraccion, nombre_atraccion, tiempo_atraccion, costo_atraccion, cupo, tipo_atraccion, descripcion, es_valida "
					+ "FROM atracciones "
					+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = atracciones.id_tipo_atraccion "
					+ "WHERE id_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			return toAtraccion(resultados);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public static int buscarID(TipoDeAtraccion tipoAtraccion) {
		try {
			String sql = "SELECT * " + "FROM tipo_atraccion " + "WHERE tipo_atraccion=?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipoAtraccion.toString());
			ResultSet resultados = statement.executeQuery();

			return resultados.getInt("id_tipo_atraccion");
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public boolean existeLaAtraccion(Atraccion atraccion) {
		try {
			String sql = "SELECT nombre_atraccion " + "FROM atracciones " + "WHERE nombre_atraccion=?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				return resultados.getString("nombre_atraccion").equals(atraccion.getNombre());
			}
			return false;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet rs) throws SQLException {
		int id_atraccion = rs.getInt("id_atraccion");
		String nombre_atraccion = rs.getString("nombre_atraccion");
		Double costo_atraccion = rs.getDouble("costo_atraccion");
		Double tiempo_atraccion = rs.getDouble("tiempo_atraccion");
		String descripcion = rs.getString("descripcion");
		int cupo = rs.getInt("cupo");
		TipoDeAtraccion tipo_atraccion = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));

		return new Atraccion(nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion, id_atraccion,
				descripcion);
	}

	@Override
	public int actualizar(Atraccion atraccion) {
		try {
			Connection conn = null;
			PreparedStatement instruccion = null;

			conn = ConnectionProvider.getConnection();
			instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
			instruccion.setString(1, atraccion.getNombre());
			instruccion.setDouble(2, atraccion.getDuracion());
			instruccion.setDouble(3, atraccion.getCosto());
			instruccion.setInt(4, atraccion.getCuposDisponibles());
			instruccion.setInt(5, buscarID(atraccion.getTipoAtraccion()));
			instruccion.setString(6, atraccion.getDescripcion());
			instruccion.setBoolean(7, atraccion.esValida());
			instruccion.setInt(8, atraccion.getID());

			return instruccion.executeUpdate();// nos devuelve la cantidad de registros afectados
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

}