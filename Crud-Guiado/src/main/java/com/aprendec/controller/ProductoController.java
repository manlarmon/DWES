package com.aprendec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.ProductoDAO;
import com.aprendec.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted ha presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("listar")) {

			ProductoDAO productoDAO = new ProductoDAO();
			List<Producto> lista = new ArrayList<>();
			try {
				lista = productoDAO.obtenerProductos();
				for (Producto producto : lista) {
					System.out.println(producto);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted a presionado la opcion listar");
		} else if (opcion.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			ProductoDAO productoDAO = new ProductoDAO();
			Producto p = new Producto();
			try {
				p = productoDAO.obtenerProducto(id);
				System.out.println(p);
				request.setAttribute("producto", p);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				productoDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		Date fechaActual = new Date();

		if (opcion.equals("guardar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			
			String nombre = request.getParameter("nombre");
			String precioStr = request.getParameter("precio");
			String cantidadStr = request.getParameter("cantidad");
			if (nombre.isBlank() || nombre == null) {
				
			}
			if (!isNumeric(precioStr)) {
				
			}else if (!isNumeric(cantidadStr)) {
				
			}
			producto.setNombre(request.getParameter("nombre"));

			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
			try {
				productoDAO.guardar(producto);
				System.out.println("Registro guardado satisfactoriamente...");
				request.setAttribute("mensaje", "Producto guardado satisfactoriamente");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block

				request.setAttribute("error", "Error al guardar el producto");

			}
		} else if (opcion.equals("editar")) {
			Producto producto = new Producto();
			ProductoDAO productoDAO = new ProductoDAO();

			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			try {
				productoDAO.editar(producto);
				System.out.println("Registro editado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// doGet(request, response);
	}
	
	private boolean isNumeric(String str) {
	    try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

}