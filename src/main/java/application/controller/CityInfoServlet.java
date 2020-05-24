package application.controller;

import application.dto.city.CityDTO;
import application.exception.NoSuchElementException;
import application.service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cityInfo")
public class CityInfoServlet extends HttpServlet {

    private final CityService cityService = new CityService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            CityDTO cityDTO = cityService.findById(id);
            req.setAttribute("id", id);
            req.setAttribute("name", cityDTO.getName());
            req.setAttribute("station", cityDTO.getStation());
        } catch (NoSuchElementException | NumberFormatException ignored) { /* Never caught */ }

        req.getRequestDispatcher("/view/CityInfo.jsp").forward(req, resp);
    }
}
