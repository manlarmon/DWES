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
import Laboral.Clases.*;
import Laboral.DAO.EmpleadosDAO;

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
		String contenido = "";

		if (opcion.equals("listarAllEmpleados")) {
			System.out.println("Usted ha presionado la opcion listar todos los empleados");

			List<Empleado> listaEmpleados = new ArrayList<>();

			try {
				listaEmpleados = empleadoDAO.findAllEmpleadosActivated();

				for (Empleado empleado : listaEmpleados) {
					System.out.println(empleado);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("listaEmpleados", listaEmpleados);
			request.setAttribute("contenido", "/Views/ListarEmpleados.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		}else if (opcion.equals("listarAllEmpleadosDesactivados")) {
			System.out.println("Usted ha presionado la opcion listar todos los empleados desactivados");

			List<Empleado> listaEmpleados = new ArrayList<>();

			try {
				listaEmpleados = empleadoDAO.findAllEmpleadosDesactivated();

				for (Empleado empleado : listaEmpleados) {
					System.out.println(empleado);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("listaEmpleadosDesactivados", listaEmpleados);
			request.setAttribute("contenido", "/Views/ListarEmpleadosDesactivados.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		}else if (opcion.equals("mostrarSalario")) {
			System.out.println("Usted ha presionado la opcion buscar un salario");
			request.setAttribute("contenido", "/Views/BuscarSalarioEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("modificarEmpleado")) {
			System.out.println("Usted ha presionado la opcion buscar empleados para modificar");
			request.setAttribute("contenido", "/Views/BuscarModificar.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("crearEmpleado")) {
			System.out.println("Usted ha presionado la opcion crear empleados");
			request.setAttribute("contenido", "/Views/CrearEmpleado.jsp");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			
		}else if (opcion.equals("editaEmpleado")) {
			String dni = request.getParameter("dni");
			Empleado emp = null;
			try {
				emp = empleadoDAO.findEmpleadoByDni(dni);
				request.setAttribute("empleado", emp);
				request.setAttribute("contenido", "/Views/EditarEmpleado.jsp");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminaEmpleado")) {
			String dni = request.getParameter("dni");
			Empleado emp = null;
			try {
				emp = empleadoDAO.findEmpleadoByDni(dni);
				request.setAttribute("empleado", emp);
				request.setAttribute("contenido", "/Views/EliminarEmpleado.jsp");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (opcion.equals("activaEmpleado")) {
			String dni = request.getParameter("dni");
			Empleado emp = null;
			try {
				emp = empleadoDAO.findEmpleadoByDni(dni);
				request.setAttribute("empleado", emp);
				request.setAttribute("contenido", "/Views/ActivarEmpleado.jsp");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (opcion.equals("volverPrincipal")) {
			System.out.println("Volviendo al menu principal...");
			request.setAttribute("contenido", "/Views/Inicio.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("volverBuscador")) {
			System.out.println("Volviendo al buscador...");
			request.setAttribute("contenido", "/Views/BuscarModificar.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
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
			request.setAttribute("contenido", "/Views/BuscarSalarioEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
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
			request.setAttribute("contenido", "/Views/BuscarModificar.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("creaEmpleado")) {
			System.out.println("Usted ha presionado la opcion crar un empleado");

			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String sexoString = request.getParameter("sexo");
			char sexo = sexoString.charAt(0);
			String categoriaString = request.getParameter("categoria");
			int categoria = Integer.parseInt(categoriaString);
			String anyosString = request.getParameter("anyos");
			double anyos = Double.parseDouble(anyosString);

			try {
				empleadoDAO.createEmpleado(dni, nombre, sexo, categoria, anyos);
				request.setAttribute("contenido", "/Views/CrearEmpleado.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (opcion.equals("editarEmpleado")) {
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
				request.setAttribute("contenido", "/Views/BuscarModificar.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminarEmpleado")) {
			System.out.println("Usted ha presionado la opcion eliminar un empleado");

			String dni = request.getParameter("dni");

			System.out.println("Eliminar empleado con dni: " + dni);

			try {
				empleadoDAO.disableEmpleadoNominaByDni(dni);
				request.setAttribute("contenido", "/Views/BuscarModificar.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (opcion.equals("activarEmpleado")) {
			System.out.println("Usted ha presionado la opcion activar un empleado");

			String dni = request.getParameter("dni");

			System.out.println("Activar empleado con dni: " + dni);

			try {
				empleadoDAO.activateEmpleadoNominaByDni(dni);
				request.setAttribute("contenido", "/Views/Inicio.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

	}

}
