package application.controller;

import application.dto.transport.TransportDTO;
import application.exception.NoSuchElementException;
import application.service.TransportService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/transportInfo")
public class TransportInfoServlet extends HttpServlet {

    @Inject
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
        } catch (NoSuchElementException | NumberFormatException e) {
            log.error(e.getMessage(), e);
        }

        req.getRequestDispatcher("/view/TransportInfo.jsp").forward(req, resp);
    }
}
