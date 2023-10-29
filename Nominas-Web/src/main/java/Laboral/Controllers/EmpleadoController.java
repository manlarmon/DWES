package Laboral.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Laboral.DBUtils.*;
import Laboral.DBUtils.EmpleadosDAO;
import Laboral.Clases.*;

/**
 * Servlet que administra las peticiones para las tablas empleados y nominas.
 */
@WebServlet(description = "administra peticiones para las tablas empleados y nominas ", urlPatterns = { "/empresa" })
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpleadosDAO empleadoDAO = new EmpleadosDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpleadoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inicializa el servlet.
	 * 
	 * @throws ServletException Si ocurre un error durante la inicialización.
	 */
	public void init() throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gestiona las solicitudes GET.
	 *
	 * @param request  La solicitud HTTP.
	 * @param response La respuesta HTTP.
	 * @throws ServletException Si ocurre un error durante la solicitud.
	 * @throws IOException      Si ocurre un error de E/S durante la solicitud.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("listarAllEmpleados")) {
			System.out.println("Usted ha presionado la opcion listar todos los empleados");

			List<Empleado> listaEmpleados = new ArrayList<>();

			try {
				listaEmpleados = empleadoDAO.findAllEmpleados();

				for (Empleado empleado : listaEmpleados) {
					System.out.println(empleado);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("listaEmpleados", listaEmpleados);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/ListarEmpleados.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("mostrarSalario")) {
			System.out.println("Usted ha presionado la opcion buscar un salario");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarSalarioEmpleado.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("modificarEmpleado")) {
			System.out.println("Usted ha presionado la opcion buscar empleados para modificar");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarModificar.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("editarEmpleado")) {
			String dni = request.getParameter("dni");
			Empleado emp = null;
			try {
				emp = empleadoDAO.findEmpleadoByDni(dni);
				request.setAttribute("empleado", emp);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/EditarEmpleado.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}

		} else if (opcion.equals("eliminarEmpleado")) {
			String dni = request.getParameter("dni");
			Empleado emp = null;
			try {
				emp = empleadoDAO.findEmpleadoByDni(dni);
				request.setAttribute("empleado", emp);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/EliminarEmpleado.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (opcion.equals("volverPrincipal")) {
			System.out.println("Volviendo al menu principal...");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("volverBuscador")) {
			System.out.println("Volviendo al buscador...");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarModificar.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * Gestiona las solicitudes POST.
	 *
	 * @param request  La solicitud HTTP recibida.
	 * @param response La respuesta HTTP que se enviará.
	 * @throws ServletException Si ocurre un error durante la solicitud.
	 * @throws IOException      Si ocurre un error de E/S durante la solicitud.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion.equals("buscarSalario")) {
			List<String> listaSalarios = new ArrayList<>();

			String dni = request.getParameter("dni");

			if (dni != null && !dni.trim().isEmpty()) {
				try {
					listaSalarios = empleadoDAO.findNominasLikeDni(dni);
					if (listaSalarios.isEmpty()) {
						System.out.println("No hay empleados según tu criterio de búsqueda");
					} else {
						System.out.println("Empleados encontrados:");
						for (String empleado : listaSalarios) {
							System.out.println(empleado);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("listaSalarios", listaSalarios);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarSalarioEmpleado.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("buscarModificar")) {
			List<Empleado> listaModificar = new ArrayList<>();

			String dni = request.getParameter("dni");

			String dato = request.getParameter("dato");
			String valor = request.getParameter("valor");

			if (dato != null && valor != null && !dato.isEmpty() && !valor.isEmpty()) {
				// Realiza la búsqueda según el campo especificado
				try {
					switch (dato) {
						case "dni":
							listaModificar = empleadoDAO.findEmpleadosLikeDNI(valor);
							break;
						case "nombre":
							listaModificar = empleadoDAO.findEmpleadosLikeNombre(valor);
							break;
						case "sexo":
							listaModificar = empleadoDAO.findEmpleadosBySexo(valor);
							break;
						case "categoria":
							int categoria = Integer.parseInt(valor);
							listaModificar = empleadoDAO.findEmpleadosByCategoria(categoria);
							break;
						case "anyos":
							double anyos = Double.parseDouble(valor);
							listaModificar = empleadoDAO.findEmpleadosByAnyos(anyos);
							break;
						default:
							break;
					}

					if (listaModificar.isEmpty()) {
						System.out.println("No hay empleados según tu criterio de búsqueda");
					} else {
						System.out.println("Empleados encontrados:");
						for (Empleado empleado : listaModificar) {
							System.out.println(empleado);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			request.setAttribute("listaModificar", listaModificar);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarModificar.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("editarEmpleado")) {
			System.out.println("Usted ha presionado la opcion modificar un empleado");

			String dni = request.getParameter("dni");

			System.out.println("Editar empleado con dni: " + dni);

			String nombre = request.getParameter("nombre");

			String sexoString = request.getParameter("sexo");
			char sexo = sexoString.charAt(0);

			String categoriaString = request.getParameter("categoria");
			int categoria = Integer.parseInt(categoriaString);

			String anyosString = request.getParameter("anyos");
			double anyos = Double.parseDouble(anyosString);

			try {
				empleadoDAO.updateEmpleado(dni, nombre, sexo, categoria, anyos);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarModificar.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminarEmpleado")) {
			System.out.println("Usted ha presionado la opcion eliminar un empleado");

			String dni = request.getParameter("dni");

			System.out.println("Eliminar empleado con dni: " + dni);

			try {
				empleadoDAO.deleteEmpleadoNominaByDni(dni);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/BuscarModificar.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
