<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Listar Productos</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

h1 {
	text-align: center;
	background-color: #007bff;
	color: #fff;
	padding: 10px;
}

table {
	width: 80%;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid #ccc;
	border-collapse: collapse;
	box-shadow: 3px 3px 5px #888;
}

table th, table td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
}

table th {
	background-color: #0074cc;
	color: #fff;
}

a {
	text-decoration: none;
	color: #007bff;
	font-weight: bold;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<h1>Listar Productos</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Cantidad</th>
			<th>Precio</th>
			<th>Fecha Creación</th>
			<th>Fecha Actualización</th>
			<th>Acción</th>
		</tr>
		<c:forEach var="producto" items="${lista}">
			<tr>
				<td><a
					href="productos?opcion=editar&id=<c:out value="${producto.id}"></c:out>"><c:out
							value="${producto.id}"></c:out></a></td>
				<td><c:out value="${producto.nombre}"></c:out></td>
				<td><c:out value="${producto.cantidad}"></c:out></td>
				<td><c:out value="${producto.precio}"></c:out></td>
				<td><c:out value="${producto.fechaCrear}"></c:out></td>
				<td><c:out value="${producto.fechaActualizar}"></c:out></td>
				<td><a
					href="productos?opcion=eliminar&id=<c:out value="${producto.id}"></c:out>">Eliminar</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
