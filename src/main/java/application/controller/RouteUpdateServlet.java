package application.controller;

import application.dao.RouteDAO;
import application.model.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/routeUpdate")
public class RouteUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Route route = Route.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .departureId(Long.parseLong(req.getParameter("departure")))
                .destinationId(Long.parseLong(req.getParameter("destination")))
                .departureTime(req.getParameter("newDepartureTime"))
                .arrivalTime(req.getParameter("newArrivalTime"))
                .transportId(Long.parseLong(req.getParameter("transport_kind")))
                .build();

        (new RouteDAO()).updateById(route.getId(), route);
        resp.sendRedirect("/view/MainPage.jsp");
    }
}
