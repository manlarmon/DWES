<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>Editar Empleado</title>

</head>
<body>

	<div class=formularioEmpleado>

		<form action="empresa" method="post">
			<h1>Editar Empleado</h1>

			<div class="boton-container">
				<!-- Enlace para volver al Menú Principal -->
				<a href="empresa?opcion=volverBuscador">
					<button class="volver-button">Volver al buscador</button>
				</a>
			</div>

			<c:set var="empleado" value="${empleado}"></c:set>
			<input type="hidden" name="opcion" value="editarEmpleado" /> <input
				type="hidden" name="dni" value="${empleado.dni}" />
			<table>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre"
						value="${empleado.nombre}" required /></td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><input type="text" name="sexo" value="${empleado.sexo}"
						required pattern="[MmFf]"
						title="Por favor, introduce solo 'M' o 'F' (mayúsculas o minúsculas)." />
					</td>
				</tr>
				<tr>
					<td>Categoria:</td>
					<td><input type="text" name="categoria"
						value="${empleado.categoria}" required pattern="^[1-9]|10$"
						title="Por favor, introduce un número del 1 al 10." />
					</td>
				</tr>
				<tr>
					<td>Años:</td>
					<td><input type="text" name="anyos" value="${empleado.anyos}"
						required pattern="^(0*[1-9]\d*(\.\d+)?|0*\.\d*[1-9]\d*)$"
						title="Por favor, introduce un número mayor que 0." />
					</td>
				</tr>
			</table>
			<input type="submit" value="Guardar" />
		</form>
	</div>

</body>
</html>
