package application.controller;

import application.dto.city.CityDTO;
import application.exception.NoSuchElementException;
import application.service.CityService;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/cityInfo")
public class CityInfoServlet extends HttpServlet {

    @EJB
    private CityService cityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            CityDTO cityDTO = cityService.findById(id);
            req.setAttribute("id", id);
            req.setAttribute("name", cityDTO.getName());
            req.setAttribute("station", cityDTO.getStation());
        } catch (NoSuchElementException | NumberFormatException e) {
            log.error(e.getMessage(), e);
        }

        req.getRequestDispatcher("/view/CityInfo.jsp").forward(req, resp);
    }
}
