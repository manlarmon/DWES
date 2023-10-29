<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Eliminar Empleado</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 50%;
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 20px;
	box-shadow: 3px 3px 5px #888;
}

h1 {
	text-align: center;
	padding: 10px;
	color: #333;
}

form {
	width: 100%;
}

table {
	width: 100%;
	margin: 10px 0;
}

table tr {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 10px 0;
}

table td {
	flex: 1;
	padding: 10px;
}

input[type="text"] {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	color: #B4B4B3;
}

input[type="submit"] {
	background-color: #0074cc;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
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
	<div class="container">
		<h1>Eliminar Empleado</h1>
		<div class="boton-container">
			<a href="empresa?opcion=volverBuscador">
				<button class="volver-button">Volver al Menú Principal</button>
			</a>
		</div>
		<form action="empresa" method="post">
			<c:set var="empleado" value="${empleado}"></c:set>
			<input type="hidden" name="opcion" value="eliminarEmpleado">
			<input type="hidden" name="dni" value="${empleado.dni}">
			<table>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="dni" value="${empleado.dni}"
						disabled="disabled"></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre"
						value="${empleado.nombre}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><input type="text" name="sexo" value="${empleado.sexo}"
						disabled="disabled"></td>
				</tr>
				<tr>
					<td>Categoria:</td>
					<td><input type="text" name="categoria"
						value="${empleado.categoria}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>Años:</td>
					<td><input type="text" name="anyos" value="${empleado.anyos}"
						disabled="disabled"></td>
				</tr>
			</table>
			<input type="submit" value="Eliminar">
		</form>
	</div>
</body>
</html>
