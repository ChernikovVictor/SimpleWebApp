package application.controller;

import application.dto.city.CityDTO;
import application.dto.route.RouteDTO;
import application.dto.transport.TransportDTO;
import application.exception.InsertionFailedException;
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
import java.util.List;

@WebServlet("/addRoute")
public class RouteAddServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @EJB
    private CityService cityService;

    @EJB
    private TransportService transportService;

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
        CityDTO departure = cityService.findById(departureId);

        Long destinationId = Long.parseLong(req.getParameter("destination"));
        CityDTO destination = cityService.findById(destinationId);

        Long transportId = Long.parseLong(req.getParameter("transport_kind"));
        TransportDTO transport = transportService.findById(transportId);

        return RouteDTO.builder()
                .index(Integer.parseInt(req.getParameter("index")))
                .departure(departure)
                .destination(destination)
                .departureTime(req.getParameter("departure_time"))
                .arrivalTime(req.getParameter("arrival_time"))
                .transport(transport)
                .build();
    }
}
