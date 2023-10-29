<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Listar Empleados</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
      }

      h1 {
        text-align: center;
        color: #333;
      }

      table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }

      th,
      td {
        border: 1px solid #ccc;
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

      .DNI {
        font-weight: bold;
        color: #007bff;
      }

      .DNI:hover {
        text-decoration: underline;
      }

      .boton-container {
        text-align: center;
        margin-top: 20px;
      }

      .volver-button {
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
    <h1>Listar Empleados</h1>
    <div class="boton-container">
      <!-- Enlace para volver al Menú Principal -->
      <a href="empresa?opcion=volverPrincipal">
        <button class="volver-button">Volver al Menú Principal</button>
      </a>
    </div>
    <table>
      <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Sexo</th>
        <th>Categoría</th>
        <th>Años trabajados</th>
      </tr>
      <c:forEach var="empleado" items="${listaEmpleados}">
        <tr>
          <!-- DNI del empleado -->
          <td class="DNI"><c:out value="${empleado.dni}" /></td>
          <!-- Nombre del empleado -->
          <td><c:out value="${empleado.nombre}" /></td>
          <!-- Sexo del empleado -->
          <td><c:out value="${empleado.sexo}" /></td>
          <!-- Categoría del empleado -->
          <td><c:out value="${empleado.categoria}" /></td>
          <!-- Años trabajados por el empleado -->
          <td><c:out value="${empleado.anyos}" /></td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
