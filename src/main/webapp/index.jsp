<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Medicamentos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #83a4d4, #b6fbff);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 40px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.2);
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-bottom: 30px;
        }

        label {
            font-weight: bold;
            color: #34495e;
        }

        select, input[type="text"] {
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 8px;
            font-size: 14px;
        }

        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s ease-in-out;
        }

        button:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #eaf2f8;
        }

        .no-result {
            text-align: center;
            color: #e74c3c;
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Buscar Medicamentos</h1>

    <!-- Formulario de búsqueda -->
    <form action="filtro" method="post">
    <label for="filtro">Tipo de filtro:</label>
        <select id="filtro" name="filtro" required>
            <option value="especialidad">Por Especialidad</option>
            <option value="tipo">Por Tipo de Medicamento</option>
        </select>

        <label for="valor">Valor:</label>
        <input type="text" id="valor" name="valor" placeholder="Ingrese el valor a buscar" required>

        <button type="submit">Buscar</button>
    </form>

    <!-- Resultados -->
    <%
        List<String[]> resultados = (List<String[]>) request.getAttribute("resultados");
        if (resultados != null) {
            if (resultados.isEmpty()) {
    %>
    <p class="no-result">No se encontraron medicamentos.</p>
    <%
    } else {
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Especialidad</th>
            <th>Precio</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (String[] fila : resultados) {
        %>
        <tr>
            <td><%= fila[0] %></td>
            <td><%= fila[1] %></td>
            <td><%= fila[2] %></td>
            <td><%= fila[3] %></td>
            <td>S/ <%= fila[4] %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
            }
        }
    %>
</div>

</body>
</html>
