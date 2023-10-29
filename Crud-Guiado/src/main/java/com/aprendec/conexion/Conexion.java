package com.aprendec.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Conexion {

	public static Connection getConn() throws SQLException {
		final String USER = "root";
		final String PASS = "12345678";
		final String DB_NAME = "productos";
		final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;
		Connection conn = null;
		
		conn = DriverManager.getConnection(CONN_URL, USER, PASS);
		return conn;

	}
}