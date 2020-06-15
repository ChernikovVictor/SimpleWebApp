package application.controller;

import application.dto.transport.TransportDTO;
import application.exception.NoSuchElementException;
import application.service.TransportService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transportInfo")
public class TransportInfoServlet extends HttpServlet {

    @EJB
    private TransportService transportService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            TransportDTO transport = transportService.findById(id);
            req.setAttribute("id", id);
            req.setAttribute("kind", transport.getKind().name());
            req.setAttribute("name", transport.getName());
            req.setAttribute("capacity", transport.getCapacity());
        } catch (NoSuchElementException | NumberFormatException ignored) { /* Never caught */ }

        req.getRequestDispatcher("/view/TransportInfo.jsp").forward(req, resp);
    }
}
