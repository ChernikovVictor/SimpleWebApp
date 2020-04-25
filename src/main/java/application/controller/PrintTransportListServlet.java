package application.controller;

import application.dao.TransportDAO;
import application.model.Transport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/transportsComboBox")
public class PrintTransportListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Transport> transports = (new TransportDAO()).findAll();
        String optionsHTML = transports.stream().map(this::transportToOptionHTML).collect(Collectors.joining());
        resp.getWriter().write(optionsHTML);

    }

    private String transportToOptionHTML(Transport transport) {
        return String.format("<option value=\"%d\">%s %s", transport.getId(), transport.getKind(), transport.getName());
    }
}
