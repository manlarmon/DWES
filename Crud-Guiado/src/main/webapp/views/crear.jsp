<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Crear Producto</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

h1 {
	text-align: center;
	background-color: #0074cc;
	color: #fff;
	padding: 10px;
}

form {
	width: 50%;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 20px;
	box-shadow: 3px 3px 5px #888;
}

table {
	width: 100%;
}

table tr {
	margin: 10px 0;
}

table td {
	padding: 10px;
}

input[type="text"] {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
}

input[type="submit"] {
	background-color: #0074cc;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.success-message {
    background-color: #4CAF50;
    color: #fff;
    padding: 10px;
    text-align: center;
}

.error-message {
    background-color: #FF5733;
    color: #fff;
    padding: 10px;
    text-align: center;
}

</style>
</head>
<body>
	<h1>Crear Producto</h1>
	<form action="productos" method="post">
		<input type="hidden" name="opcion" value="guardar">
		<table>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre"></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad"></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio"></td>
			</tr>
		</table>

		<%-- Para mostrar un mensaje de éxito --%>
		<c:if test="${mensaje}">
			<div class="success-message">${mensaje}</div>
		</c:if>

		<%-- Para mostrar un mensaje de error --%>
		<c:if test="${error}">
			<div class="error-message">${error}</div>
		</c:if>

		<input type="submit" value="Guardar">
	</form>
</body>
</html>
