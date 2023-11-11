<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Crear Empleado</title>

</head>
<body>

	<div class=formularioEmpleado>
		<form action="empresa" method="post">
			<h1>Crear Empleado</h1>
	
			<input type="hidden" name="opcion" value="creaEmpleado" />
			<table>
				<tr>
					<td>DNI:</td>
					<td><input type="text" name="dni" pattern="[0-9]{8}[A-Za-z]"
						required /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" pattern="[A-Za-z]+"
						required /></td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><input type="text" name="sexo" required pattern="[MmFf]"
						title="Por favor, introduce solo 'M' o 'F' (mayúsculas o minúsculas)." />
					</td>
				</tr>
				<tr>
					<td>Categoria:</td>
					<td><input type="text" name="categoria" required
						pattern="^[1-9]|10$"
						title="Por favor, introduce un número del 1 al 10." /></td>
				</tr>
				<tr>
					<td>Años:</td>
					<td><input type="text" name="anyos" required
						pattern="^(0*[1-9]\d*(\.\d+)?|0*\.\d*[1-9]\d*)$"
						title="Por favor, introduce un número mayor que 0." /></td>
				</tr>
			</table>
			<input type="submit" value="Crear" />
		</form>
	</div>


</body>
</html>