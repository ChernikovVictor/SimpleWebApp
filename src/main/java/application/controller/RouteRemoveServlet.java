package application.controller;

import application.exception.NoSuchElementException;
import application.service.RouteService;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/removeRoute")
public class RouteRemoveServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            routeService.removeById(id);
            List<Long> listId = (List<Long>) req.getSession().getAttribute("listId");
            listId.remove(id);
        } catch (NumberFormatException | NoSuchElementException e) {
            log.error(e.getMessage(), e);
        }

        resp.sendRedirect("view/MainPage.jsp");
    }
}
