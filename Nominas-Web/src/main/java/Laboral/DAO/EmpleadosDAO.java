package Laboral.DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Laboral.Clases.Empleado;
import Laboral.Clases.Nomina;
import Laboral.DBUtils.ConexDB;
import Laboral.Excepciones.DatosNoCorrectosException;

/**
 * Esta clase proporciona métodos para interactuar con una base de datos
 * relacionada con empleados y nóminas.
 * Permite realizar operaciones como la creación, actualización, búsqueda y
 * eliminación de registros de empleados y nóminas.
 */

public class EmpleadosDAO {

	// Para las consultas
	Statement st = null;
	PreparedStatement pstEmpleado = null;
	PreparedStatement pstNomina = null;
	ResultSet rs = null;

	/**
	 * Crea un nuevo registro de empleado en la base de datos y calcula su sueldo en
	 * la tabla de nóminas.
	 *
	 * @param nombre    El nombre del empleado.
	 * @param dni       El número de identificación (DNI) del empleado.
	 * @param sexo      El género del empleado.
	 * @param categoria La categoría del empleado.
	 * @param anyos     Los años trabajados por el empleado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void createEmpleado(String dni, String nombre, char sexo, int categoria, double anyos) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setString(1, dni);
			pstEmpleado.setString(2, nombre);
			pstEmpleado.setString(3, String.valueOf(sexo));
			pstEmpleado.setInt(4, categoria);
			pstEmpleado.setDouble(5, anyos);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro insertado con éxito en la tabla empleados.");

				// Creo un empleado ficticio para poder calcular la nomina
				// ya que esta no es una propiedad de empleado.

				Empleado empleadoFicticio = new Empleado(nombre, dni, 'F', categoria, anyos);
				Nomina nomina = new Nomina();
				double sueldo = nomina.sueldo(empleadoFicticio);

				// Aqui se llama al metodo createNomina para crear su sueldo a partir de sus
				// datos.
				createNomina(dni, sueldo);
			} else {
				System.out.println("No se pudo insertar el registro en la tabla empleados.");
			}

			conn.commit();
			pstEmpleado.close();
			conn.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Crea un nuevo registro de nómina para un empleado en la base de datos.
	 *
	 * @param dni    El número de identificación (DNI) del empleado.
	 * @param sueldo El sueldo del empleado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void createNomina(String dni, double sueldo) throws SQLException {
		Connection conn = null;

		try {

			conn = getConnection();
			conn.setAutoCommit(false);
			// Consulta SQL para insertar en la tabla de nomina

			String sql = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";

			pstNomina = conn.prepareStatement(sql);
			pstNomina.setString(1, dni);
			pstNomina.setDouble(2, sueldo);
			int filasAfectadasNomina = pstNomina.executeUpdate();

			if (filasAfectadasNomina > 0) {
				System.out.println("Sueldo insertado en la tabla de nómina con éxito.");
			} else {
				System.out.println("No se pudo insertar el sueldo en la tabla de nómina.");
			}

			conn.commit();
			pstNomina.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene una lista de todos los empleados almacenados en la base de datos.
	 *
	 * @return Una lista de objetos Empleado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public List<Empleado> findAllEmpleados() throws SQLException {
		Connection conn = null;

		conn = getConnection();
		conn.setAutoCommit(false);
		List<Empleado> empleadosDB = new ArrayList<>();

		try {

			String sql = "SELECT * FROM empleados;";
			pstEmpleado = conn.prepareStatement(sql);
			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosDB.add(empleadoLeido);
			}

			conn.commit();
			pstEmpleado.close();
			conn.close();
			// for (Empleado empleado : empleadosDB) {
			// empleado.Imprime();
			// }
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosDB;

	}

	public List<Empleado> findAllEmpleadosActivated() throws SQLException {
		Connection conn = null;

		conn = getConnection();
		conn.setAutoCommit(false);
		List<Empleado> empleadosDB = new ArrayList<>();

		try {

			String sql = "SELECT * FROM empleados WHERE eliminado = 0;";
			pstEmpleado = conn.prepareStatement(sql);
			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosDB.add(empleadoLeido);
			}

			conn.commit();
			pstEmpleado.close();
			conn.close();
			// for (Empleado empleado : empleadosDB) {
			// empleado.Imprime();
			// }
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosDB;

	}
	
	public List<Empleado> findAllEmpleadosDesactivated() throws SQLException {
		Connection conn = null;

		conn = getConnection();
		conn.setAutoCommit(false);
		List<Empleado> empleadosDB = new ArrayList<>();

		try {

			String sql = "SELECT * FROM empleados WHERE eliminado = 1;";
			pstEmpleado = conn.prepareStatement(sql);
			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosDB.add(empleadoLeido);
			}

			conn.commit();
			pstEmpleado.close();
			conn.close();
			// for (Empleado empleado : empleadosDB) {
			// empleado.Imprime();
			// }
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosDB;

	}
	
	/**
	 * Busca un empleado por su número de identificación (DNI) en la base de datos.
	 *
	 * @param dniIntroducido El DNI del empleado a buscar.
	 * @return El objeto Empleado encontrado o null si no se encuentra.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Empleado findEmpleadoByDni(String dniIntroducido) throws SQLException {

		Connection conn = null;

		Empleado empleadoEncontrado = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM empleados WHERE dni = ?";
			pstEmpleado = conn.prepareStatement(sql);
			pstEmpleado.setString(1, dniIntroducido);

			rs = pstEmpleado.executeQuery();

			if (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				empleadoEncontrado = new Empleado(nombre, dni, sexo, categoria, anyos);
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadoEncontrado;
	}

	/**
	 * Busca empleados cuyo DNI contenga una cadena proporcionada.
	 *
	 * @param dniIntroducido La cadena a buscar en los DNIs de los empleados.
	 * @return Una lista de objetos Empleado que coinciden con la búsqueda.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> findEmpleadosLikeDNI(String dniIntroducido) throws SQLException {
		List<Empleado> empleadosLikeDNI = new ArrayList<>();
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM empleados WHERE dni LIKE ? AND eliminado = 0;";
			pstEmpleado = conn.prepareStatement(sql);
			pstEmpleado.setString(1, "%" + dniIntroducido + "%"); // Usamos % para que coincida con cualquier parte del
																	// DNI
			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosLikeDNI.add(empleadoLeido);
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosLikeDNI;
	}

	/**
	 * Busca empleados cuyo nombre contenga una cadena proporcionada.
	 *
	 * @param nombreIntroducido La cadena a buscar en los nombres de los empleados.
	 * @return Una lista de objetos Empleado que coinciden con la búsqueda.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> findEmpleadosLikeNombre(String nombreIntroducido) throws SQLException {
		List<Empleado> empleadosLikeNombre = new ArrayList<>();
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM empleados WHERE nombre LIKE ? AND eliminado = 0;";
			pstEmpleado = conn.prepareStatement(sql);
			pstEmpleado.setString(1, "%" + nombreIntroducido + "%"); // Usamos % para que coincida con cualquier parte
																		// del nombre
			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosLikeNombre.add(empleadoLeido);
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosLikeNombre;
	}

	/**
	 * Busca empleados por género.
	 *
	 * @param valor El género a buscar (por ejemplo, 'H' para hombres o 'M' para
	 *              mujeres).
	 * @return Una lista de objetos Empleado que coinciden con la búsqueda.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> findEmpleadosBySexo(String valor) throws SQLException {
		List<Empleado> empleadosBySexo = new ArrayList<>();
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM empleados WHERE sexo LIKE ? AND eliminado = 0;";
			pstEmpleado = conn.prepareStatement(sql);
			char valorChar = valor.charAt(0);
			pstEmpleado.setString(1, "%" + valorChar + "%");
			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosBySexo.add(empleadoLeido);
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosBySexo;
	}

	/**
	 * Busca empleados por años trabajados.
	 *
	 * @param anyoIntroducido Los años trabajados a buscar.
	 * @return Una lista de objetos Empleado que coinciden con la búsqueda.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> findEmpleadosByAnyos(double anyoIntroducido) throws SQLException {
		List<Empleado> empleadosByAnyos = new ArrayList<>();
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM empleados WHERE anyos = ? AND eliminado = 0;";
			pstEmpleado = conn.prepareStatement(sql);
			pstEmpleado.setDouble(1, anyoIntroducido);
			;

			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosByAnyos.add(empleadoLeido);
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosByAnyos;
	}

	/**
	 * Recupera una lista de empleados por una categoría específica.
	 *
	 * @param categoriaIntroducida La categoría utilizada para filtrar empleados.
	 * @return Una lista de empleados que coinciden con la categoría especificada.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public List<Empleado> findEmpleadosByCategoria(int categoriaIntroducida) throws SQLException {
		List<Empleado> empleadosByCategoria = new ArrayList<>();
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM empleados WHERE categoria = ? AND eliminado = 0;";
			pstEmpleado = conn.prepareStatement(sql);
			pstEmpleado.setInt(1, categoriaIntroducida);
			;

			rs = pstEmpleado.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				char sexo = rs.getString("sexo").charAt(0);
				int categoria = rs.getInt("categoria");
				double anyos = rs.getDouble("anyos");

				Empleado empleadoLeido = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleadosByCategoria.add(empleadoLeido);
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return empleadosByCategoria;
	}

	/**
	 * Encuentra un registro de nómina mediante el DNI proporcionado.
	 *
	 * @param dniIntroducido El DNI utilizado para buscar un registro de nómina.
	 * @return Una cadena que representa la información de la nómina para el
	 *         empleado.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public String findNominaByDni(String dniIntroducido) throws SQLException {
		String nominaEncontrada = null;
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT dni, sueldo FROM nominas WHERE dni = ? AND eliminado = 0";
			PreparedStatement pstNomina = conn.prepareStatement(sql);
			pstNomina.setString(1, dniIntroducido);

			ResultSet rs = pstNomina.executeQuery();

			if (rs.next()) {
				String dni = rs.getString("dni");
				double sueldo = rs.getDouble("sueldo");
				nominaEncontrada = "Dni: " + dni + ", Sueldo: " + sueldo;
			} else {
				nominaEncontrada = "No se encontró una nómina para el DNI proporcionado.";
			}
			conn.commit();
			pstEmpleado.close();
			conn.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return nominaEncontrada;
	}

	/**
	 * Encuentra registros de nómina similares al DNI proporcionado.
	 *
	 * @param dniIntroducido El DNI parcial o completo utilizado para buscar
	 *                       registros de nómina.
	 * @return Una lista de cadenas que representan la información de nómina para
	 *         empleados que coinciden.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public List<String> findNominasLikeDni(String dniIntroducido) throws SQLException {
		List<String> nominasLikeDni = new ArrayList<>();
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM nominas WHERE dni LIKE ? AND eliminado = 0;";
			pstNomina = conn.prepareStatement(sql);
			pstNomina.setString(1, "%" + dniIntroducido + "%"); // Usamos % para que coincida con cualquier parte del
																// DNI
			rs = pstNomina.executeQuery();

			while (rs.next()) {
				String dni = rs.getString("dni");
				double salario = rs.getDouble("sueldo");

				nominasLikeDni.add("DNI: " + dni + "; Salario: " + salario);
			}
			conn.commit();
			pstNomina.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

		return nominasLikeDni;
	}

	/**
	 * Actualiza la información de un empleado en función de los parámetros
	 * proporcionados.
	 *
	 * @param dniBuscado El DNI del empleado que se actualizará.
	 * @param nombre     El nuevo nombre para el empleado.
	 * @param sexo       El nuevo género para el empleado.
	 * @param categoria  La nueva categoría para el empleado.
	 * @param anyos      Los nuevos años de servicio para el empleado.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public void updateEmpleado(String dniBuscado, String nombre, char sexo, int categoria, double anyos)
			throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = "UPDATE empleados SET nombre = ?, sexo = ?,categoria = ?, anyos = ? WHERE dni = ? ";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setString(1, nombre);
			pstEmpleado.setString(2, String.valueOf(sexo));
			pstEmpleado.setInt(3, categoria);
			pstEmpleado.setDouble(4, anyos);
			pstEmpleado.setString(5, dniBuscado);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro actualizado con éxito en la tabla empleados.");
				conn.commit();
				pstEmpleado.close();
				conn.close();
				// Aqui se llama al metodo updateNomina para actualizar su sueldo.
				updateNomina(dniBuscado, categoria, anyos);

			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza el nombre de un empleado.
	 *
	 * @param dniBuscado El DNI del empleado cuyo nombre se actualizará.
	 * @param nombre     El nuevo nombre para el empleado.
	 */
	public void updateEmpleadoNombre(String dniBuscado, String nombre) {
		Connection conn = null;

		try {
			String sql = "UPDATE empleados SET nombre = ?  WHERE dni = ?";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setString(1, nombre);
			pstEmpleado.setString(2, dniBuscado);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro actualizado con éxito en la tabla empleados.");
			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	/**
	 * Actualiza el género de un empleado.
	 *
	 * @param dniBuscado El DNI del empleado cuyo género se actualizará.
	 * @param sexo       El nuevo género para el empleado.
	 */
	public void updateEmpleadoSexo(String dniBuscado, char sexo) {
		Connection conn = null;

		try {
			String sql = "UPDATE empleados SET sexo = ? WHERE dni = ?";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setString(1, String.valueOf(sexo));
			pstEmpleado.setString(2, dniBuscado);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro actualizado con éxito en la tabla empleados.");
			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	/**
	 * Actualiza la categoría de un empleado y desencadena una actualización de la
	 * nómina correspondiente.
	 *
	 * @param dniBuscado El DNI del empleado que se actualizará.
	 * @param categoria  La nueva categoría para el empleado.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public void updateEmpleadoCategoria(String dniBuscado, int categoria) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE empleados SET categoria = ? WHERE dni = ?";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setInt(1, categoria);
			pstEmpleado.setString(2, dniBuscado);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro actualizado con éxito en la tabla empleados.");
				String sql2 = "Select anyos from empleados WHERE dni = ?";
				pstEmpleado = conn.prepareStatement(sql2);
				pstEmpleado.setString(1, dniBuscado); // Asegúrate de asignar el valor del DNI

				rs = pstEmpleado.executeQuery();
				if (rs.next()) {
					double anyos = rs.getDouble(1); // Obtener el valor de "anyos"
					updateNomina(dniBuscado, categoria, anyos);
				}

			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}

			conn.commit();
			pstEmpleado.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	};

	/**
	 * Actualiza los años de servicio de un empleado y desencadena una actualización
	 * de la nómina correspondiente.
	 *
	 * @param dniBuscado El DNI del empleado que se actualizará.
	 * @param anyos      Los nuevos años de servicio para el empleado.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public void updateEmpleadoAnyos(String dniBuscado, double anyos) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE empleados SET anyos = ? WHERE dni = ?";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setDouble(1, anyos);
			pstEmpleado.setString(2, dniBuscado);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro actualizado con éxito en la tabla empleados.");
				String sql2 = "Select categoria from empleados WHERE dni = ?";
				pstEmpleado = conn.prepareStatement(sql2);
				pstEmpleado.setString(1, dniBuscado); // Configura el parámetro dni en la consulta

				rs = pstEmpleado.executeQuery();
				int categoria = 0;

				if (rs.next()) {
					categoria = rs.getInt("categoria");

					updateNomina(dniBuscado, categoria, anyos);
				} else {
					System.out.println("No se encontró la categoría del empleado.");
				}
				conn.commit();
				pstEmpleado.close();
				conn.close();
			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	};

	/**
	 * Actualiza los años de servicio y la categoría de todos los empleados,
	 * desencadenando actualizaciones de las nóminas correspondientes.
	 *
	 * @param anyos     Los nuevos años de servicio para todos los empleados.
	 * @param categoria La nueva categoría para todos los empleados.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public void updateAllEmpleados(double anyos, int categoria) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE empleados SET anyos = ?, categoria = ?";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setDouble(1, anyos);
			pstEmpleado.setInt(2, categoria);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {

				updateAllNominas(categoria, anyos);

			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza los registros de nómina de todos los empleados en función de su
	 * categoría y años de servicio, desencadenando actualizaciones de las nóminas
	 * correspondientes.
	 *
	 * @param categoria La categoría utilizada para calcular las nóminas.
	 * @param anyos     Los años de servicio utilizados para calcular las nóminas.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	private void updateAllNominas(int categoria, double anyos) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			// Consulta SQL para insertar en la tabla de nomina
			// Creo un empleado ficticio para poder calcular la nomina
			// ya que esta no es una propiedad de empleado.
			Empleado empleadoFicticio = new Empleado("Ficticio", "11111111A", 'G', categoria, anyos);
			Nomina nomina = new Nomina();
			double sueldo = nomina.sueldo(empleadoFicticio);

			String sql = "UPDATE nominas SET sueldo = ?";
			pstNomina = conn.prepareStatement(sql);
			pstNomina.setDouble(1, sueldo);

			int filasAfectadasNomina = pstNomina.executeUpdate();

			if (filasAfectadasNomina > 0) {
				System.out.println("Sueldo actualizado de todos los empleados en la tabla de nómina con éxito.");
			} else {
				System.out.println("No se pudo actualizar los sueldos en la tabla de nómina.");
			}
			conn.commit();
			pstNomina.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza el registro de nómina de un empleado en función de su DNI,
	 * categoría y años de servicio, desencadenando actualizaciones de la nómina
	 * correspondiente.
	 *
	 * @param dni       El DNI del empleado.
	 * @param categoria La categoría utilizada para calcular la nómina.
	 * @param anyos     Los años de servicio utilizados para calcular la nómina.
	 * @throws DatosNoCorrectosException Si los datos del empleado no son correctos.
	 * @throws SQLException              Si se produce un error en la base de datos.
	 */
	private void updateNomina(String dni, int categoria, double anyos) throws DatosNoCorrectosException, SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			// Consulta SQL para insertar en la tabla de nomina
			// Creo un empleado ficticio para poder calcular la nomina
			// ya que esta no es una propiedad de empleado.
			Empleado empleadoFicticio = new Empleado("Ficticio", "11111111A", 'G', categoria, anyos);
			Nomina nomina = new Nomina();
			double sueldo = nomina.sueldo(empleadoFicticio);

			String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
			pstNomina = conn.prepareStatement(sql);
			pstNomina.setDouble(1, sueldo);
			pstNomina.setString(2, dni);

			int filasAfectadasNomina = pstNomina.executeUpdate();

			if (filasAfectadasNomina > 0) {
				System.out.println("Sueldo actualizado en la tabla de nómina con éxito.");
			} else {
				System.out.println("No se pudo actualizar el sueldo en la tabla de nómina.");
			}

			conn.commit();
			pstNomina.close();
			conn.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un empleado y sus registros de nómina correspondientes en función de
	 * su DNI.
	 *
	 * @param dni El DNI del empleado que se eliminará.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	public void deleteEmpleadoNominaByDni(String dni) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			// Primero tengo que eliminar el empleado de la tabla nominas ya que dni es FK
			String sql = "DELETE FROM nominas WHERE dni = ?";
			pstNomina = conn.prepareStatement(sql);
			pstNomina.setString(1, dni);
			int filasAfectadas = pstNomina.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Empleado eliminado con éxito en la tabla nominas.");

				conn.commit();
				pstNomina.close();

				sql = "DELETE FROM empleados WHERE dni = ?";
				pstEmpleado = conn.prepareStatement(sql);
				pstEmpleado.setString(1, dni);
				filasAfectadas = pstEmpleado.executeUpdate();

				if (filasAfectadas > 0) {
					System.out.println("Empleado eliminado con éxito en la tabla empleados.");
					conn.commit();
					pstEmpleado.close();
				} else {
					System.out.println("No se ha podido eliminar el empleado de la tabla empleados.");
				}

			} else {
				System.out.println("No se ha podido eliminar el empleado de la tabla nominas.");
			}

			conn.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}

	public void disableEmpleadoNominaByDni(String dni) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			// Primero tengo que eliminar el empleado de la tabla nominas ya que dni es FK
			String sql = "UPDATE nominas SET eliminado = 1 WHERE dni = ?";
			pstNomina = conn.prepareStatement(sql);
			pstNomina.setString(1, dni);
			int filasAfectadas = pstNomina.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Empleado desactivado con éxito en la tabla nominas.");

				conn.commit();
				pstNomina.close();

				sql = "UPDATE empleados SET eliminado = 1 WHERE dni = ?";
				pstEmpleado = conn.prepareStatement(sql);
				pstEmpleado.setString(1, dni);
				filasAfectadas = pstEmpleado.executeUpdate();

				if (filasAfectadas > 0) {
					System.out.println("Empleado desactivado con éxito en la tabla empleados.");
					conn.commit();
					pstEmpleado.close();
				} else {
					System.out.println("No se ha podido desactivar el empleado de la tabla empleados.");
				}

			} else {
				System.out.println("No se ha podido desactivar el empleado de la tabla nominas.");
			}

			conn.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}
	
	public void activateEmpleadoNominaByDni(String dni) throws SQLException {
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			// Primero tengo que eliminar el empleado de la tabla nominas ya que dni es FK
			String sql = "UPDATE nominas SET eliminado = 0 WHERE dni = ?";
			pstNomina = conn.prepareStatement(sql);
			pstNomina.setString(1, dni);
			int filasAfectadas = pstNomina.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Empleado activado con éxito en la tabla nominas.");

				conn.commit();
				pstNomina.close();

				sql = "UPDATE empleados SET eliminado = 0 WHERE dni = ?";
				pstEmpleado = conn.prepareStatement(sql);
				pstEmpleado.setString(1, dni);
				filasAfectadas = pstEmpleado.executeUpdate();

				if (filasAfectadas > 0) {
					System.out.println("Empleado activado con éxito en la tabla empleados.");
					conn.commit();
					pstEmpleado.close();
				} else {
					System.out.println("No se ha podido activar el empleado de la tabla empleados.");
				}

			} else {
				System.out.println("No se ha podido activar el empleado de la tabla nominas.");
			}

			conn.close();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
	}
	/**
	 * Obtiene una conexión de base de datos.
	 *
	 * @return Una conexión de base de datos.
	 * @throws SQLException Si se produce un error en la base de datos.
	 */
	private Connection getConnection() throws SQLException {
		return ConexDB.getConn();
	}
}
