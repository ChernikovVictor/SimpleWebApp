package application.controller;

import application.dao.CityDAO;
import application.dao.TransportDAO;
import application.dto.route.RouteDTO;
import application.entity.City;
import application.entity.Transport;
import application.exception.NoSuchElementException;
import application.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/routeUpdate")
public class RouteUpdateServlet extends HttpServlet {

    private final RouteService routeService = new RouteService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            RouteDTO routeDTO = createRoute(req);
            routeService.update(routeDTO);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/view/MainPage.jsp");
    }

    private RouteDTO createRoute(HttpServletRequest req) throws NoSuchElementException {

        Long departureId = Long.parseLong(req.getParameter("departure"));
        City departure = (new CityDAO()).findById(departureId).orElseThrow(NoSuchElementException::new);

        Long destinationId = Long.parseLong(req.getParameter("destination"));
        City destination = (new CityDAO()).findById(destinationId).orElseThrow(NoSuchElementException::new);

        Long transportId = Long.parseLong(req.getParameter("transport_kind"));
        Transport transport = (new TransportDAO()).findById(transportId).orElseThrow(NoSuchElementException::new);

        return RouteDTO.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .index(Integer.parseInt(req.getParameter("index")))
                .departure(departure)
                .destination(destination)
                .departureTime(req.getParameter("newDepartureTime"))
                .arrivalTime(req.getParameter("newArrivalTime"))
                .transport(transport)
                .build();
    }
}
