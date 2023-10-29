<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Buscar salario empleado</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
      }

      .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }

      h1 {
        text-align: center;
        color: #333;
      }

      form {
        text-align: center;
        margin: 20px;
      }

      label {
        display: block;
        margin: 10px 0;
      }

      input[type="text"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
      }

      input[type="submit"] {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }

      input[type="submit"]:hover {
        background-color: #0056b3;
      }

      a {
        display: block;
        text-align: center;
        margin-top: 10px;
        text-decoration: none;
        color: #007bff;
      }

      .boton-container {
        text-align: center;
        margin: 10px;
      }

      .volver-button {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
        border-radius: 5px;
      }

      .volver-button:hover {
        background-color: #0056b3;
      }

      ul {
        list-style-type: none;
        padding: 10px;
        margin: 10px;
      }

      li {
        padding: 10px;
        border: 1px solid #ddd;
        margin: 5px 0;
        background-color: #f2f2f2;
      }

      li:hover {
        background-color: #ddd;
      }

      a {
        text-decoration: none;
        color: #007bff;
      }

      a:hover {
        text-decoration: underline;
      }

      .form-group {
        margin: 10px;
      }
    </style>
  </head>
  <body>
    <h1>Buscador de salarios</h1>
    <div class="boton-container">
      <!-- Enlace para volver al Menú Principal -->
      <a href="empresa?opcion=volverPrincipal">
        <button class="volver-button">Volver al Menú Principal</button>
      </a>
    </div>
    <div class="container">
      <form action="empresa" method="post">
        <input type="hidden" name="opcion" value="buscarSalario" />
        <div class="form-group">
          <label for="dni">DNI o parte del DNI del empleado:</label>
          <input
            type="text"
            name="dni"
            pattern="^\d{1,8}[A-Za-z]?$"
            required
            title="Por favor, introduce entre 1 y 9 caracteres. Si son 9 el último debe ser una letra"
          />
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
