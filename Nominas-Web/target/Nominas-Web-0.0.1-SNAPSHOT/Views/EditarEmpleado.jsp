<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Editar Empleado</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
      }

      h1 {
        text-align: center;
        padding: 10px;
        color: #333;
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
    <h1>Editar Empleado</h1>
    <div class="boton-container">
      <!-- Enlace para volver al buscador -->
      <a href="empresa?opcion=volverBuscador">
        <button class="volver-button">Volver al buscador</button>
      </a>
    </div>
    <form action="empresa" method="post">
      <c:set var="empleado" value="${empleado}"></c:set>
      <input type="hidden" name="opcion" value="editarEmpleado" />
      <input type="hidden" name="dni" value="${empleado.dni}" />
      <table>
        <tr>
          <td>Nombre:</td>
          <td>
            <input
              type="text"
              name="nombre"
              value="${empleado.nombre}"
              required
            />
          </td>
        </tr>
        <tr>
          <td>Sexo:</td>
          <td>
            <input
              type="text"
              name="sexo"
              value="${empleado.sexo}"
              required
              pattern="[MmFf]"
              title="Por favor, introduce solo 'M' o 'F' (mayúsculas o minúsculas)."
            />
          </td>
        </tr>
        <tr>
          <td>Categoria:</td>
          <td>
            <input
              type="text"
              name="categoria"
              value="${empleado.categoria}"
              required
              pattern="^[1-9]|10$"
              title="Por favor, introduce un número del 1 al 10."
            />
          </td>
        </tr>
        <tr>
          <td>Años:</td>
          <td>
            <input
              type="text"
              name="anyos"
              value="${empleado.anyos}"
              required
              pattern="^(0*[1-9]\d*(\.\d+)?|0*\.\d*[1-9]\d*)$"
              title="Por favor, introduce un número mayor que 0."
            />
          </td>
        </tr>
      </table>
      <input type="submit" value="Guardar" />
    </form>
  </body>
</html>
