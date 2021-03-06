package application.controller;

import application.dto.route.RouteDTO;
import application.entity.TransportKinds;
import application.service.RouteService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* Сервлет, формирующий список айдшишников запрошенных маршрутов */
@WebServlet("/findRoutesByTransportKind")
public class RoutesFindByTransportKindServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO: искать только индексы, а не дтошки целиком
        List<RouteDTO> routeDTOs;
        String kind = req.getParameter("kind");
        if (Objects.isNull(kind)) {
            routeDTOs = Collections.emptyList();
        } else if (kind.equals("trains")) {
            routeDTOs = routeService.findAllByTransportKind(TransportKinds.TRAIN);
        } else if (kind.equals("planes")) {
            routeDTOs = routeService.findAllByTransportKind(TransportKinds.PLANE);
        } else {
            routeDTOs = routeService.findAll();
        }

        List<Long> listId = routeDTOs.stream().map(RouteDTO::getId).collect(Collectors.toCollection(LinkedList::new));
        req.getSession().setAttribute("listId", listId);
        resp.sendRedirect("view/MainPage.jsp");
    }
}
