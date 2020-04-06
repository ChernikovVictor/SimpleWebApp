package application.controller;

import application.dao.CityDAO;
import application.exception.NoSuchElementException;
import application.model.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cityInfo")
public class CityInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            City city = (new CityDAO()).findById(id);
            req.setAttribute("id", id);
            req.setAttribute("name", city.getName());
            req.setAttribute("station", city.getStation());
        } catch (NoSuchElementException | NumberFormatException ignored) { /* Never caught */ }

        req.getRequestDispatcher("/view/CityInfo.jsp").forward(req, resp);
    }
}
