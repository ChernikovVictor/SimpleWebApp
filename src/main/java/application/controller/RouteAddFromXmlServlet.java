package application.controller;

import application.dto.RouteDTO;
import application.dto.RouteMapper;
import application.model.Route;

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

    private RouteService routeService = new RouteService();
    private RouteMapper routeMapper = new RouteMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            List<Route> routes = (List<Route>) req.getSession().getAttribute("routes");
            Route route = routes.parallelStream().filter(r -> r.getId().equals(id))
                    .findAny().orElseThrow(NoSuchElementException::new);

            RouteDTO routeDTO = routeMapper.routeToRouteDto(route).orElseThrow(NoSuchElementException::new);
            routeService.addWithId(routeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("view/LoadRoutesPage.jsp");
        }
    }
}
