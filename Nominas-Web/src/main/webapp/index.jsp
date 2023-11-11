<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Menú de Opciones Empleados</title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">

</head>
<body>

	<div class = "container">
		<%@include file="Views/Components/Header.jsp"%>

		<div class="content">
			<c:if test="${empty requestScope.contenido}">
				<c:import url="Views/Inicio.jsp" />
			</c:if>

			<c:if test="${not empty requestScope.contenido}">
				<c:import url="${requestScope.contenido}" />
			</c:if>

		</div>

		<%@include file="Views/Components/Footer.jsp"%>
	
	</div>




</body>
</html>
