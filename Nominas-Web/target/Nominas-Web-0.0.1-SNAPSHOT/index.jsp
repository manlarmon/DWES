<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Menú de Opciones Empleados</title>
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

      table,
      td {
        border: 1px solid #ccc;
      }

      td {
        padding: 12px;
        text-align: center;
      }

      a {
        text-decoration: none;
        color: #007bff;
      }

      a:hover {
        text-decoration: underline;
      }
    </style>
  </head>
  <body>
    <h1>Menú de Opciones Empleados</h1>
    <table>
      <tr>
        <td>
          <!-- Enlace para mostrar la lista de empleados -->
          <a href="empresa?opcion=listarAllEmpleados">Mostrar Empleados</a>
        </td>
      </tr>
      <tr>
        <td>
          <!-- Enlace para mostrar los salarios -->
          <a href="empresa?opcion=mostrarSalario">Mostrar Salarios</a>
        </td>
      </tr>
      <tr>
        <td>
          <!-- Enlace para modificar los datos del empleado -->
          <a href="empresa?opcion=modificarEmpleado">Modificar Datos</a>
        </td>
      </tr>
    </table>
  </body>
</html>
