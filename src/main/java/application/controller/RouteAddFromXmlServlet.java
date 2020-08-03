package application.controller;

import application.dto.route.RouteDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import application.exception.NoSuchElementException;
import application.service.RouteService;
import lombok.extern.slf4j.Slf4j;

/* Сервлет добавляет выбранный пользователем маршрут из xml-файла в базу данных */
@Slf4j
@WebServlet("/addRouteFromXml")
public class RouteAddFromXmlServlet extends HttpServlet {

    @Inject
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer index = Integer.parseInt(req.getParameter("index"));
            List<RouteDTO> routes = (List<RouteDTO>) req.getSession().getAttribute("routes");
            RouteDTO routeDTO = routes.parallelStream().filter(r -> r.getIndex().equals(index))
                    .findAny().orElseThrow(NoSuchElementException::new);

            routeService.makePersistent(routeDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            resp.sendRedirect("view/LoadRoutesPage.jsp");
        }
    }
}
