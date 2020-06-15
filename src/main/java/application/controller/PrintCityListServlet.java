package application.controller;

import application.dto.city.CityDTO;
import application.service.CityService;

import javax.ejb.EJB;
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

    @EJB
    private CityService cityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CityDTO> cities = cityService.findAll();
        String optionsHTML = cities.stream().map(this::cityToOptionHTML).collect(Collectors.joining());
        resp.getWriter().write(optionsHTML);

    }

    private String cityToOptionHTML(CityDTO city) {
        return String.format("<option value=\"%d\">%s %s", city.getId(), city.getName(), city.getStation());
    }
}
