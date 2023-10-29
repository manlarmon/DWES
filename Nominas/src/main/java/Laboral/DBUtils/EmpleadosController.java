 package Laboral.DBUtils;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Laboral.Clases.Empleado;
import Laboral.Clases.Nomina;
import Laboral.Excepciones.DatosNoCorrectosException;

public class EmpleadosController {

	// Para las consultas
	Statement st = null;
	PreparedStatement pstEmpleado = null;
	PreparedStatement pstNomina = null;
	ResultSet rs = null;
	Connection conn = null;

	public void createEmpleado(String nombre, String dni, char sexo, int categoria, double anyos) {
		try {
			String sql = "INSERT INTO empleados (nombre, dni, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setString(1, nombre);
			pstEmpleado.setString(2, dni);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createNomina(String dni, double sueldo) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Empleado> findAllEmpleados() {

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

			// for (Empleado empleado : empleadosDB) {
			// empleado.Imprime();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empleadosDB;

	}

	public Empleado findEmpleadoByDni(String dniIntroducido) {
		Empleado empleadoEncontrado = null;
		try {
			String sql = "Select * from empleados where dni = ?";
			pstEmpleado = conn.prepareStatement(sql);
			pstEmpleado.setString(1, dniIntroducido);

			rs = pstEmpleado.executeQuery();
			String dni = rs.getString("dni");
			String nombre = rs.getString("nombre");
			char sexo = rs.getString("sexo").charAt(0);
			int categoria = rs.getInt("categoria");
			double anyos = rs.getDouble("anyos");

			empleadoEncontrado = new Empleado(nombre, dni, sexo, categoria, anyos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empleadoEncontrado;
	}

	public String findNominaByDni(String dniIntroducido) {
		String nominaEncontrada = null;
		try {
			String sql = "SELECT dni, sueldo FROM nominas WHERE dni = ?";
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nominaEncontrada;
	}

	public void updateEmpleado(String dniBuscado, String nombre, char sexo, int categoria, double anyos) {
		try {
			String sql = "UPDATE empleados SET nombre = ?, sexo = ?,categoria = ?, anyos = ? WHERE dni = ?";
			pstEmpleado = conn.prepareStatement(sql);

			pstEmpleado.setString(1, nombre);
			pstEmpleado.setString(2, String.valueOf(sexo));
			pstEmpleado.setInt(3, categoria);
			pstEmpleado.setDouble(4, anyos);
			pstEmpleado.setString(5, dniBuscado);

			int filasAfectadas = pstEmpleado.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Registro actualizado con éxito en la tabla empleados.");

				// Aqui se llama al metodo updateNomina para actualizar su sueldo.
				updateNomina(dniBuscado, categoria, anyos);

			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateEmpleadoNombre(String dniBuscado, String nombre) {
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

	public void updateEmpleadoSexo(String dniBuscado, char sexo) {
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

	public void updateEmpleadoCategoria(String dniBuscado, int categoria) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public void updateEmpleadoAnyos(String dniBuscado, double anyos) {
		try {
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


			} else {
				System.out.println("No se pudo actualizar el registro en la tabla empleados.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public void updateAllEmpleados(double anyos, int categoria) {
		try {
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
			e.printStackTrace();
		}
	}

	private void updateAllNominas(int categoria, double anyos) {
		try {

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateNomina(String dni, int categoria, double anyos) throws DatosNoCorrectosException {
		try {

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void deleteEmpleadoByDni(String dni) {
	// try {

	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// Recibe la conn de la clase que maneja la apertura y el cierre de la DB

	public Connection setConn(Connection conexion) {
		return conn = conexion;
	}
}
