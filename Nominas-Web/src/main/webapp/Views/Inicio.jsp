<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


		<table>
				<h1>Menú de Opciones</h1>
			<tr>
				<td>
					<!-- Enlace para mostrar la lista de empleados --> <a
					href="empresa?opcion=listarAllEmpleados">Mostrar todos los Empleados</a>
				</td>
			</tr>
			<tr>
				<td>
					<!-- Enlace para mostrar los salarios --> <a
					href="empresa?opcion=mostrarSalario">Buscar Salarios</a>
				</td>
			</tr>
			<tr>
				<td>
					<!-- Enlace para modificar los datos del empleado --> <a
					href="empresa?opcion=modificarEmpleado">Buscar para Modificar</a>
				</td>
			</tr>
			<tr>
				<td>
					<!-- Enlace para modificar los datos del empleado --> <a
					href="empresa?opcion=crearEmpleado">Crear empleados</a>
				</td>
			</tr>
			<tr>
				<td>
					<!-- Enlace para modificar los datos del empleado --> <a
					href="empresa?opcion=listarAllEmpleadosDesactivados">Mostrar empleados desactivados</a>
				</td>
			</tr>

		</table>

</body>
</html>