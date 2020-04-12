package application.controller;

import application.dao.TransportDAO;
import application.exception.NoSuchElementException;
import application.model.Transport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transportInfo")
public class TransportInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Transport transport = (new TransportDAO()).findById(id).orElseThrow(NoSuchElementException::new);
            req.setAttribute("id", id);
            req.setAttribute("kind", transport.getKind().name());
            req.setAttribute("name", transport.getName());
            req.setAttribute("capacity", transport.getCapacity());
        } catch (NoSuchElementException | NumberFormatException ignored) { /* Never caught */ }

        req.getRequestDispatcher("/view/TransportInfo.jsp").forward(req, resp);
    }
}
