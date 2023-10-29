package Laboral.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexDB {
	
	public static Connection getConn() throws SQLException {
	    // Conexion
	    final String USER = "root";
	    final String PASS = "12345678";
	    final String DB_NAME = "nominas";
	    final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;
	    Connection conn = null;
	    conn = DriverManager.getConnection(CONN_URL, USER, PASS);
        return conn;

	}
	
}
