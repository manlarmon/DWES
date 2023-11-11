<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<title>Buscar salario empleado</title>
<style>












</style>
</head>
<body>
	<h1>Buscador de salarios</h1>

	<div class="buscador">
		<form action="empresa" method="post">
			<input type="hidden" name="opcion" value="buscarSalario" />
			<div class="form-group">
				<label for="dni">DNI o parte del DNI del empleado:</label> <input
					type="text" name="dni" pattern="^\d{1,8}[A-Za-z]?$" required
					title="Por favor, introduce entre 1 y 9 caracteres. Si son 9 el último debe ser una letra" />
			</div>
			<div class="form-group">
				<input type="submit" value="Buscar Salario" />
			</div>
		</form>

		<c:if test="${not empty listaSalarios}">
			<h2>Salarios:</h2>
			<ul>
				<c:forEach var="salario" items="${listaSalarios}">
					<li><c:out value="${salario}" /></li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
</body>
</html>
