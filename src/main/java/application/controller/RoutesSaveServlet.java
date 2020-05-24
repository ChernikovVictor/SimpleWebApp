package application.controller;

import application.bean.XmlLoaderBean;
import application.dto.route.RouteDTO;
import application.dto.route.RouteMapper;
import application.service.RouteService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/* Сервлет, сохраняющий маршруты в файл xml */
@WebServlet("/saveAsXml")
public class RoutesSaveServlet extends HttpServlet {

    @EJB
    private XmlLoaderBean xmlLoaderBean;

    private final RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* Получить айдишники сохраняемых маршрутов */
        Object listId = req.getSession().getAttribute("listId");
        if (listId == null) {
            return;
        }

        /* Получить маршруты */
        List<RouteDTO> routeDTOs = routeService.findAllByIds((List<Long>) listId);

        /* Сохранить маршруты */
        RouteMapper mapper = new RouteMapper();
        xmlLoaderBean.saveAsXml(mapper.routesDTOsToRoutes(routeDTOs));

        resp.sendRedirect("view/MainPage.jsp");
    }
}
