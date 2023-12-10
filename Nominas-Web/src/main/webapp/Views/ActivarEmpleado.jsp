<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>Eliminar Empleado</title>


</head>
<body>
	<div class="formularioEmpleado">
		<h1>Activar Empleado</h1>
		<div class="boton-container">
			<a href="empresa?opcion=volverBuscador">
				<button class="volver-button">Volver al buscador</button>
			</a>
		</div>
		<form action="empresa" method="post">
			<c:set var="empleado" value="${empleado}"></c:set>
			<input type="hidden" name="opcion" value="activarEmpleado" /> <input
				type="hidden" name="dni" value="${empleado.dni}" />
			<table>
				<tr>
					<td>DNI:</td>
					<td><input type="text" name="dni" value="${empleado.dni}"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre"
						value="${empleado.nombre}" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><input type="text" name="sexo" value="${empleado.sexo}"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td>Categoría:</td>
					<td><input type="text" name="categoria"
						value="${empleado.categoria}" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td>Años:</td>
					<td><input type="text" name="anyos" value="${empleado.anyos}"
						disabled="disabled" /></td>
				</tr>
			</table>
			<input type="submit" value="Activar" />
		</form>
	</div>
</body>
</html>