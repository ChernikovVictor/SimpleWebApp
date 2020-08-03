package application.controller;

import application.dto.route.RouteDTO;
import application.service.RouteService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* Сервлет, формирующий список айдшишников запрошенных маршрутов */
@WebServlet("/findRoutesByCityName")
public class RoutesFindByCityServlet extends HttpServlet {

    @Inject
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO: искать только индексы, а не дтошки целиком
        String cityName = req.getParameter("cityName");
        if (Objects.nonNull(cityName)) {
            List<RouteDTO> routeDTOs = routeService.findByCityName(cityName);
            List<Long> listId = routeDTOs.stream().map(RouteDTO::getId).collect(Collectors.toCollection(LinkedList::new));
            req.getSession().setAttribute("listId", listId);
        }

        resp.sendRedirect("view/MainPage.jsp");
    }
}
