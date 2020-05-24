package application.controller;

import application.dao.CityDAO;
import application.dao.TransportDAO;
import application.dto.route.RouteDTO;
import application.entity.City;
import application.entity.Transport;
import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addRoute")
public class RouteAddServlet extends HttpServlet {

    private final RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RouteDTO routeDTO = createRoute(req);
            List<Long> listId = (List<Long>) req.getSession().getAttribute("listId");
            listId.add(routeService.add(routeDTO));
        } catch (InsertionFailedException | NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("/view/MainPage.jsp");
        }
    }

    private RouteDTO createRoute(HttpServletRequest req) throws NoSuchElementException {

        Long departureId = Long.parseLong(req.getParameter("departure"));
        City departure = (new CityDAO()).findById(departureId).orElseThrow(NoSuchElementException::new);

        Long destinationId = Long.parseLong(req.getParameter("destination"));
        City destination = (new CityDAO()).findById(destinationId).orElseThrow(NoSuchElementException::new);

        Long transportId = Long.parseLong(req.getParameter("transport_kind"));
        Transport transport = (new TransportDAO()).findById(transportId).orElseThrow(NoSuchElementException::new);

        return RouteDTO.builder()
                .departure(departure)
                .destination(destination)
                .departureTime(req.getParameter("departure_time"))
                .arrivalTime(req.getParameter("arrival_time"))
                .transport(transport)
                .build();
    }
}
