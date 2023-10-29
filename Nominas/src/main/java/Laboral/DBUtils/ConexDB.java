package Laboral.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexDB {
    // Conexion
    final String USER = "root";
    final String PASS = "12345678";
    final String DB_NAME = "nominas";
    final String CONN_URL = "jdbc:mariadb://localhost:3306/nominas";
    Connection conn = null;

    public void openConnection() {
        try {
            conn = DriverManager.getConnection(CONN_URL, USER, PASS);
            System.out.println("Conexión válida: " + conn.isValid(10));
            System.out.println("Estado del autocommit: " + conn.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection() {

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexion cerrada");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConn() {
        return conn;
    }
}
