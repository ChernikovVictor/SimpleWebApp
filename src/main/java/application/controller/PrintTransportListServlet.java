package application.controller;

import application.dto.transport.TransportDTO;
import application.service.TransportService;

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

    final TransportService transportService = new TransportService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TransportDTO> transports = transportService.findAll();
        String optionsHTML = transports.stream().map(this::transportToOptionHTML).collect(Collectors.joining());
        resp.getWriter().write(optionsHTML);

    }

    private String transportToOptionHTML(TransportDTO transport) {
        return String.format("<option value=\"%d\">%s %s", transport.getId(), transport.getKind(), transport.getName());
    }
}
