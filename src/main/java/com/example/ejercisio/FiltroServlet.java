package com.example.ejercisio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/filtro")
public class FiltroServlet extends HttpServlet {
    private MedicamentoDAO dao = new MedicamentoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String tipoFiltro = request.getParameter("filtro"); // coincide con el <select name="filtro"> en JSP
        String valor = request.getParameter("valor");

        List<String[]> resultados = new ArrayList<>();

        if (tipoFiltro != null && valor != null && !valor.trim().isEmpty()) {
            switch (tipoFiltro) {
                case "especialidad":
                    resultados = dao.getMedicamentosPorEspecialidad(valor);
                    break;
                case "tipo":
                    resultados = dao.getMedicamentosPorTipo(valor);
                    break;
                case "procedimiento":
                    resultados = dao.getMedicamentosPorProcedimiento(valor);
                    break;
            }

        }

        // Guardar en request y enviar al JSP
        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
