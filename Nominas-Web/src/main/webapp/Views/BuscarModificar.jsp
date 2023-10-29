<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Modificar Empleado</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}

h2 {
	text-align: center;
	color: #333;
}

form {
	max-width: 400px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-bottom: 10px;
	font-weight: bold;
}

select {
	width: 96%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="text"] {
	width: 90%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

select {
	height: 40px;
}

input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	padding: 10px 20px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

table {
	width: 90%;
	margin: 20px auto;
	border-collapse: collapse;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #192655;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}

a {
	text-decoration: none;
	color: #007bff;
}

a:hover {
	text-decoration: underline;
}

.DNI {
	color: #007bff;
	font-weight: bold;
}

.DNI:hover {
	text-decoration: underline;
}

.boton-container {
	text-align: center;
	margin: 20px;
}

.volver-button {
	text-align: center;
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	font-size: 16px;
}

.volver-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<h2>Buscar Empleado/s a modificar</h2>
	<div class="boton-container">
		<a href="empresa?opcion=volverPrincipal">
			<button class="volver-button">Volver al Menú Principal</button>
		</a>
	</div>

	<form action="empresa" method="post">
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

	<c:if test="${not empty listaModificar}">
		<table>
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
					<td><c:out value="${empleado.anyos}"></c:out></td>
					<td><a
						href="empresa?opcion=eliminarEmpleado&dni=<c:out value="${empleado.dni}"></c:out>">Eliminar</a>
						/ <a
						href="empresa?opcion=editarEmpleado&dni=<c:out value="${empleado.dni}"></c:out>">Editar</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>