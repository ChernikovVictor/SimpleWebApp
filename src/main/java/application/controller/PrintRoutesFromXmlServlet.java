package application.controller;

import application.dto.route.RouteDTO;
import application.service.RouteService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

/* Сервлет выводит данные из xml-файла в форме html-таблицы */
@WebServlet("/printRoutesFromXml")
public class PrintRoutesFromXmlServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object routes = req.getSession().getAttribute("routes");
        if (Objects.nonNull(routes) && routes instanceof List<?>) {
            writeTable((List<RouteDTO>) routes, resp.getWriter());
        }
    }

    private void writeTable(List<RouteDTO> routeDTOs, PrintWriter writer) {
        writer.write("<table id=\"table\" width=\"90%\" border=\"1px\" cellspacing=\"0px\" cellpadding=\"4px\" align=\"center\">\n");
        writeHeader(writer);
        writeRows(routeDTOs, writer);
        writer.write("</table>");
    }

    private void writeHeader(PrintWriter writer) {
        writer.write("<tr>\n" +
                "<th>index</th>\n" +
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
            String row = cell(routeDTO.getIndex().toString()) +
                    cell(routeDTO.getDeparture().getName()) +
                    cell(routeDTO.getDestination().getName()) +
                    cell(routeDTO.getDepartureTime()) +
                    cell(routeDTO.getArrivalTime()) +
                    cell(routeDTO.getTransport().getName());

            if (routeService.contains(routeDTO)) {
                row += cell("Есть в базе");
            } else {
                row += cell("<a href=\"/addRouteFromXml?id=" + routeDTO.getId().toString() + "\">Добавить в базу</a>");
            }

            writer.write(row);
            writer.write("</tr>\n");
        });
    }

    private String cell(String s) {
        return "<td>" + s + "</td>\n";
    }
}
