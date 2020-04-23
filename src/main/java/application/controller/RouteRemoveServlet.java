package application.controller;

import application.exception.NoSuchElementException;
import application.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/removeRoute")
public class RouteRemoveServlet extends HttpServlet {

    private final RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            routeService.removeById(id);
            List<Long> listId = (List<Long>) req.getSession().getAttribute("listId");
            listId.remove(id);
        } catch (NumberFormatException | NoSuchElementException ignored) {}

        resp.sendRedirect("view/MainPage.jsp");
    }
}
