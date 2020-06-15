package application.controller;

import application.dto.route.RouteDTO;
import application.exception.NoSuchElementException;
import application.service.RouteService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/routeInfo")
public class RouteInfoServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            RouteDTO routeDTO = routeService.findById(id);
            req.setAttribute("id", id);
            req.setAttribute("index", routeDTO.getIndex());
            req.setAttribute("departure", routeDTO.getDeparture());
            req.setAttribute("destination", routeDTO.getDestination());
            req.setAttribute("departure_time", routeDTO.getDepartureTime());
            req.setAttribute("arrival_time", routeDTO.getArrivalTime());
            req.setAttribute("transport", routeDTO.getTransport());
        } catch (NoSuchElementException | NumberFormatException ignored) { /* Never caught */ }

        req.getRequestDispatcher("/view/RouteInfo.jsp").forward(req, resp);
    }
}
