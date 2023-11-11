<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Modificar Empleado</title>

</head>
<body>

	<div class = buscadorModificar>


	<form action="empresa" method="post">
			<h2>Buscar Empleado/s a modificar</h2>
		<input type="hidden" name="opcion" value="buscarModificar"> <label
			for="dato">Selecciona un dato para buscar:</label> <select
			name="dato" id="dato">
			<option value="dni">DNI</option>
			<option value="nombre">Nombre</option>
			<option value="sexo">Sexo</option>
			<option value="categoria">Categoría</option>
			<option value="anyos">Años trabajados</option>
		</select> <label for="valor">Introduce el valor a buscar:</label> <input
			type="text" id="valor" name="valor" required> <input
			type="submit" value="BuscarModificar">
	</form>
	</div>


	<c:if test="${not empty listaModificar}">
		<table class = tablaListar>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años trabajados</th>
				<th>Acciones</th>
			</tr>
			<c:forEach var="empleado" items="${listaModificar}">
				<tr>
					<td class="DNI"><c:out value="${empleado.dni}"></c:out></td>
					<td><c:out value="${empleado.nombre}"></c:out></td>
					<td><c:out value="${empleado.sexo}"></c:out></td>
					<td><c:out value="${empleado.categoria}"></c:out></td>
					<td><c:out value="${empleado.anyos}"></c:out> </td>
					<td><a href="empresa?opcion=eliminaEmpleado&dni=<c:out value="${empleado.dni}"></c:out>">Eliminar</a>
						/ <a href="empresa?opcion=editaEmpleado&dni=<c:out value="${empleado.dni}"></c:out>">Editar</a>
						</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
