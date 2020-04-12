package application.controller;

import application.dto.RouteDTO;
import application.exception.NoSuchElementException;
import application.model.TransportKinds;
import application.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/routesTable")
public class PrintRoutesTableServlet extends HttpServlet {

    private final RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<RouteDTO> routeDTOs;
        String kind = req.getParameter("kind");
        try {
            if (kind == null) {
                return;
            } else if (kind.equals("trains")) {
                routeDTOs = routeService.findAllByTransportKind(TransportKinds.TRAIN);
            } else if (kind.equals("planes")) {
                routeDTOs = routeService.findAllByTransportKind(TransportKinds.PLANE);
            } else {
                routeDTOs = routeService.findAll();
            }
        } catch (NoSuchElementException e) {
            routeDTOs = new LinkedList<>();
        }

        writeTable(routeDTOs, resp.getWriter());
    }

    private void writeTable(List<RouteDTO> routeDTOs, PrintWriter writer) {
        writer.write("<table id=\"table\" width=\"90%\" border=\"1px\" cellspacing=\"0px\" cellpadding=\"4px\" align=\"center\">\n");
        writeHeader(writer);
        writeRows(routeDTOs, writer);
        writer.write("</table>");
    }

    private void writeHeader(PrintWriter writer) {
        writer.write("<tr>\n" +
                "<th>id</th>\n" +
                "<th>departure</th>\n" +
                "<th>destination</th>\n" +
                "<th>departure_time</th>\n" +
                "<th>arrival_time</th>\n" +
                "<th>transport_kind</th>\n" +
                "<th>&nbsp</th>\n" +
                "</tr>");
    }

    private void writeRows(List<RouteDTO> routeDTOs, PrintWriter writer) {
        routeDTOs.forEach(routeDTO -> {
            writer.write("<tr>\n");
            String id = routeDTO.getId().toString();
            String row = cell("<a href=\"/routeInfo?id=" + id + "\">" + id + "</a>") +
                    cell("<a href=\"/cityInfo?id=" + routeDTO.getDeparture().getId() + "\">" + routeDTO.getDeparture().getName() + "</a>") +
                    cell("<a href=\"/cityInfo?id=" + routeDTO.getDestination().getId() + "\">" + routeDTO.getDestination().getName() + "</a>") +
                    cell(routeDTO.getDepartureTime()) +
                    cell(routeDTO.getArrivalTime()) +
                    cell("<a href=\"/transportInfo?id=" + routeDTO.getTransport().getId() + "\">" + routeDTO.getTransport().getName() + "</a>") +
                    cell("<a href=\"/removeRoute?id=" + id + "\">Удалить</a>");
            writer.write(row);
            writer.write("</tr>\n");
        });
    }

    private String cell(String s) {
        return "<td>" + s + "</td>\n";
    }
}
