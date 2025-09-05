package com.example.ejercisio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    private Connection newConnection() throws SQLException {
        return ConexionBD.getConnection();
    }

    // Traer por tipo
    public List<String[]> getMedicamentosPorTipo(String tipo) {
        List<String[]> lista = new ArrayList<>();
        String query = "SELECT m.codmedicamento, m.descripcionmed, t.descripcion AS tipo, " +
                "e.descripcionesp AS especialidad, m.precioventauni " +
                "FROM medicamento m " +
                "INNER JOIN tipomedic t ON m.codtipomed = t.codtipomed " +
                "INNER JOIN especialidad e ON m.codespec = e.codespec " +
                "WHERE unaccent(t.descripcion) ILIKE unaccent(?)";

        try (Connection con = newConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String param = "%" + (tipo == null ? "" : tipo) + "%";
            System.out.println("Parámetro recibido (tipo): " + param);
            ps.setString(1, param);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new String[]{
                            rs.getString("codmedicamento"),
                            rs.getString("descripcionmed"),
                            rs.getString("tipo"),
                            rs.getString("especialidad"),
                            rs.getString("precioventauni")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Traer por especialidad
    public List<String[]> getMedicamentosPorEspecialidad(String especialidad) {
        List<String[]> lista = new ArrayList<>();
        String query = "SELECT m.codmedicamento, m.descripcionmed, t.descripcion AS tipo, " +
                "e.descripcionesp AS especialidad, m.precioventauni " +
                "FROM medicamento m " +
                "INNER JOIN tipomedic t ON m.codtipomed = t.codtipomed " +
                "INNER JOIN especialidad e ON m.codespec = e.codespec " +
                "WHERE unaccent(e.descripcionesp) ILIKE unaccent(?)";

        try (Connection con = newConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String param = "%" + (especialidad == null ? "" : especialidad) + "%";
            System.out.println("Parámetro recibido (especialidad): " + param);
            ps.setString(1, param);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new String[]{
                            rs.getString("codmedicamento"),
                            rs.getString("descripcionmed"),
                            rs.getString("tipo"),
                            rs.getString("especialidad"),
                            rs.getString("precioventauni")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Traer por procedimiento (función de Postgres)
    public List<String[]> getMedicamentosPorProcedimiento(String filtro) {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT * FROM filtrar_medicamentos(?)";

        try (Connection con = newConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro == null ? "" : filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new String[]{
                            rs.getString("codmedicamento"),
                            rs.getString("descripcionmed"),
                            rs.getString("tipo"),
                            rs.getString("especialidad"),
                            rs.getString("precioventauni")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
