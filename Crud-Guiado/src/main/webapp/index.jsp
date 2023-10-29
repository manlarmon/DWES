<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menú de Opciones</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
            background-color: #0074cc;
            color: #fff;
            padding: 10px;
        }

        table {
            width: 50%;
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
            color: #0074cc;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Menú de Opciones Productos</h1>
    <table>
        <tr>
            <td><a href="productos?opcion=crear">Crear un Producto</a></td>
        </tr>
        <tr>
            <td><a href="productos?opcion=listar">Listar Productos</a></td>
        </tr>
    </table>
</body>
</html>

