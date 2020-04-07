package application.controller;

import application.dao.RouteDAO;
import application.exception.InsertionFailedException;
import application.model.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addRoute")
public class RouteAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Route route = Route.builder()
                .departureId(Long.parseLong(req.getParameter("departure")))
                .destinationId(Long.parseLong(req.getParameter("destination")))
                .departureTime(req.getParameter("departure_time"))
                .arrivalTime(req.getParameter("arrival_time"))
                .transportId(Long.parseLong(req.getParameter("transport_kind")))
                .build();
        try {
            (new RouteDAO()).add(route);
        } catch (InsertionFailedException e) {
            e.printStackTrace();
        } finally {
            resp.sendRedirect("/view/MainPage.jsp?kind=all");
        }
    }
}
