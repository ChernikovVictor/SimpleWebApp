package application.controller;

import application.dto.route.RouteDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import application.exception.NoSuchElementException;
import application.service.RouteService;

/* Сервлет вставляет выбранный пользователем маршрут из xml-файла в базу данных */
@WebServlet("/addRouteFromXml")
public class RouteAddFromXmlServlet extends HttpServlet {

    private final RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            List<RouteDTO> routes = (List<RouteDTO>) req.getSession().getAttribute("routes");
            RouteDTO routeDTO = routes.parallelStream().filter(r -> r.getId().equals(id))
                    .findAny().orElseThrow(NoSuchElementException::new);

            routeService.add(routeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("view/LoadRoutesPage.jsp");
        }
    }
}
