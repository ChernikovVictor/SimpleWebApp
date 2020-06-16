package application.controller;

import application.dto.city.CityDTO;
import application.dto.route.RouteDTO;
import application.dto.transport.TransportDTO;
import application.exception.NoSuchElementException;
import application.service.CityService;
import application.service.RouteService;
import application.service.TransportService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/routeUpdate")
public class RouteUpdateServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @EJB
    private CityService cityService;

    @EJB
    private TransportService transportService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RouteDTO routeDTO = createRoute(req);
            routeService.update(routeDTO);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("/view/MainPage.jsp");
        }
    }

    private RouteDTO createRoute(HttpServletRequest req) throws NoSuchElementException {

        Long departureId = Long.parseLong(req.getParameter("departure"));
        CityDTO departure = cityService.findById(departureId);

        Long destinationId = Long.parseLong(req.getParameter("destination"));
        CityDTO destination = cityService.findById(destinationId);

        Long transportId = Long.parseLong(req.getParameter("transport_kind"));
        TransportDTO transport = transportService.findById(transportId);

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
