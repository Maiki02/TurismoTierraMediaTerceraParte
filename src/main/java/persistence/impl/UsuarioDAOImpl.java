
package persistence.impl;

import java.sql.*;
import java.util.*;

import excepciones.*;
import model.nullobjects.NullUser;
import model.producto.*;
import model.usuario.Usuario;
import persistence.iUsuarioDAO;
import persistence.commons.*;

public class UsuarioDAOImpl implements iUsuarioDAO {

	private static final String SQL_LISTAR = "SELECT id_usuario, nombre_usuario, password, tiempo_usuario, monedas_usuario, tipo_atraccion, total_a_pagar, total_horas_gastadas, es_admin "
			+ "FROM usuarios "
			+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = usuarios.id_tipo_atraccion_favorito ";
	private static final String VALIDAS = "WHERE es_valido=1 ";
	private static final String INVALIDAS = "WHERE es_valido=0 ";

	private static final String SQL_ACTUALIZAR = "UPDATE usuarios SET tiempo_usuario = ?, monedas_usuario= ?, total_a_pagar= ?, total_horas_gastadas= ? WHERE id_usuario = ?";

	public List<Usuario> listarUsuarios(Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) {
		try {
			Connection conn = null;
			PreparedStatement instruccion = null;
			ResultSet rs = null;
			Usuario usuario = null;
			List<Usuario> usuarios = new ArrayList<Usuario>();

			conn = ConnectionProvider.getConnection();
			instruccion = conn.prepareStatement(SQL_LISTAR + VALIDAS);
			rs = instruccion.executeQuery();

			while (rs.next()) {
				try {
					usuario = toUser(rs, mapDeAtraccionesPorID, mapDePromocionesPorID);
					usuarios.add(usuario);
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

			return usuarios;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}

	}

	private List<Producto> buscarProductosComprados(int idUsuario, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement instruccion = conn
					.prepareStatement("SELECT * FROM compra_del_usuario WHERE id_usuario=?");
			instruccion.setInt(1, idUsuario);
			ResultSet rs = instruccion.executeQuery();

			List<Producto> productosComprados = new ArrayList<Producto>();
			while (rs.next()) {
				Integer idPromocion = rs.getInt("id_promocion_comprada");
				Integer idAtraccion = rs.getInt("id_atraccion_comprada");
				Producto productoComprado = null;

				if (idPromocion == 0) {
					productoComprado = mapDeAtraccionesPorID.get(idAtraccion);
				} else {
					productoComprado = mapDePromocionesPorID.get(idPromocion);
				}

				productosComprados.add(productoComprado);
			}

			return productosComprados;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public int actualizarContraseña(Usuario usuario) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement instruccion = conn
					.prepareStatement("UPDATE usuarios SET password = ? WHERE id_usuario = ?;");
			instruccion.setString(1, usuario.getPassword());
			instruccion.setInt(2, usuario.getID());
			return instruccion.executeUpdate();
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios (nombre_usuario, password, tiempo_usuario, monedas_usuario, id_tipo_atraccion_favorito) VALUES(?, ?, ?, ?, ?);";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getPassword());
			statement.setDouble(3, usuario.getHorasDisponibles());
			statement.setDouble(4, usuario.getMonedasDisponibles());
			statement.setInt(5, AtraccionDAOImpl.buscarID(usuario.getTipoFavorito()));
			return statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int actualizar(Usuario usuario) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
			instruccion.setDouble(1, usuario.getHorasDisponibles());
			instruccion.setDouble(2, usuario.getMonedasDisponibles());
			instruccion.setDouble(3, usuario.getTotalAPagar());
			instruccion.setDouble(4, usuario.getHorasGastadas());
			instruccion.setDouble(5, usuario.getID());

			return instruccion.executeUpdate();
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public Usuario find(Integer id, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) {
		try {
			String sql = "SELECT id_usuario, nombre_usuario, password, tiempo_usuario, monedas_usuario, tipo_atraccion, total_a_pagar, total_horas_gastadas, es_admin "
					+ "FROM usuarios "
					+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = usuarios.id_tipo_atraccion_favorito "
					+ "WHERE id_usuario = ? and es_valido = 1 ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados, mapDeAtraccionesPorID, mapDePromocionesPorID);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	/*
	 * private static void actualizarProductosComprados(Usuario usuario) { try {
	 * List<Producto> productosComprados = usuario.getProductosComprados();
	 * Connection conn = ConexionBDD.getConexion(); String sql =
	 * "INSERT INTO compra_del_usuario (id_usuario, id_promocion_comprada, id_atraccion_comprada) VALUES (?, ?, ?);"
	 * ;
	 * 
	 * } catch (Exception e) { throw new DatosPerdidos(e); }
	 * 
	 * }
	 */
	public Usuario findByUsername(String username, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) {
		try {
			String sql = "SELECT id_usuario, nombre_usuario, password, tiempo_usuario, monedas_usuario, tipo_atraccion, total_a_pagar, total_horas_gastadas, es_admin "
					+ "FROM usuarios "
					+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = usuarios.id_tipo_atraccion_favorito "
					+ "WHERE nombre_usuario = ? and es_valido=1 ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados, mapDeAtraccionesPorID, mapDePromocionesPorID);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet rs, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) throws SQLException {
		int idUsuario = rs.getInt("id_usuario");
		String nombre = rs.getString("nombre_usuario");
		String password = rs.getString("password");
		boolean esAdmin = rs.getInt("es_admin") == 1;
		Double monedasDisponibles = rs.getDouble("monedas_usuario");
		Double horasDisponibles = rs.getDouble("tiempo_usuario");
		TipoDeAtraccion tipoFavorito = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));
		double totalAPagar = rs.getDouble("total_a_pagar");
		double totalHorasGastadas = rs.getDouble("total_horas_gastadas");

		List<Producto> productosComprados = buscarProductosComprados(idUsuario, mapDeAtraccionesPorID,
				mapDePromocionesPorID);

		return new Usuario(idUsuario, nombre, password, monedasDisponibles, horasDisponibles, tipoFavorito, totalAPagar,
				totalHorasGastadas, productosComprados, esAdmin);
	}

	@Override
	public int agregarProductoAlItinerario(Integer id, Producto producto) {
		String sql;
		if (producto.esPromocion()) {
			sql = "INSERT INTO compra_del_usuario (id_usuario, id_promocion_comprada) VALUES (?,?)";
		} else {
			sql = "INSERT INTO compra_del_usuario (id_usuario, id_atraccion_comprada) VALUES (?,?)";
		}
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, producto.getID());

			return statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

}
