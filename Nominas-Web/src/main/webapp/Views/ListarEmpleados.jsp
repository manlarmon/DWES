<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>Listar Empleados</title>

</head>
<body>
	<h1>Listar Empleados</h1>
	

	<table class = tablaListar>
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años trabajados</th>
		</tr>
		<c:forEach var="empleado" items="${listaEmpleados}">
			<tr>
				<!-- DNI del empleado -->
				<td class="DNI"><c:out value="${empleado.dni}" /></td>
				<!-- Nombre del empleado -->
				<td><c:out value="${empleado.nombre}" /></td>
				<!-- Sexo del empleado -->
				<td><c:out value="${empleado.sexo}" /></td>
				<!-- Categoría del empleado -->
				<td><c:out value="${empleado.categoria}" /></td>
				<!-- Años trabajados por el empleado -->
				<td><c:out value="${empleado.anyos}" /></td>
			</tr>
		</c:forEach>
	</table>

	
</body>
</html>
