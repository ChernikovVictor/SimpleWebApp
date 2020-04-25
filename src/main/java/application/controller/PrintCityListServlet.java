package application.controller;

import application.dao.CityDAO;
import application.model.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/citiesComboBox")
public class PrintCityListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<City> cities = (new CityDAO()).findAll();
        String optionsHTML = cities.stream().map(this::cityToOptionHTML).collect(Collectors.joining());
        resp.getWriter().write(optionsHTML);

    }

    private String cityToOptionHTML(City city) {
        return String.format("<option value=\"%d\">%s %s", city.getId(), city.getName(), city.getStation());
    }
}
