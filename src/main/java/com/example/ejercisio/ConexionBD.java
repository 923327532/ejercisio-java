package com.example.ejercisio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/farmacia";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Lopez2003";

    public static Connection getConnection() {
        try {
            // Forzar carga del driver
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de PostgreSQL", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión: " + e.getMessage(), e);
        }
    }
}
