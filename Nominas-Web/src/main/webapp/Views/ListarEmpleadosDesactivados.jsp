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
	<h1>Lista Empleados Desactivados</h1>
		<table class = tablaListar>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años trabajados</th>
				<th>Acciones</th>
			</tr>
			<c:forEach var="empleado" items="${listaEmpleadosDesactivados}">
				<tr>
					<td class="DNI"><c:out value="${empleado.dni}"></c:out></td>
					<td><c:out value="${empleado.nombre}"></c:out></td>
					<td><c:out value="${empleado.sexo}"></c:out></td>
					<td><c:out value="${empleado.categoria}"></c:out></td>
					<td><c:out value="${empleado.anyos}"></c:out> </td>
					<td><a href="empresa?opcion=activaEmpleado&dni=<c:out value="${empleado.dni}"></c:out>">Activar</a></td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>
