package Laboral.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase proporciona métodos para establecer una conexión a la base de
 * datos.
 */
public class ConexDB {

	/**
	 * Obtiene una conexión a la base de datos.
	 * 
	 * @return Un objeto Connection que representa la conexión a la base de datos.
	 * @throws SQLException Si ocurre un error al establecer la conexión.
	 */
	public static Connection getConn() throws SQLException {
		// Información de conexión
		final String USER = "root";
		final String PASS = "12345678";
		final String DB_NAME = "nominas";
		final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;
		Connection conn = null;
		// Establecer la conexión
		conn = DriverManager.getConnection(CONN_URL, USER, PASS);
		return conn;
	}
}
