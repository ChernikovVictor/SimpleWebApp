package application.controller;

import application.bean.XmlLoaderBean;
import application.dao.XmlPathDAO;
import application.exception.NoSuchElementException;
import application.model.Route;
import application.model.XmlPath;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/loadRoutesFromXml")
public class RoutesLoadServlet extends HttpServlet {

    @EJB
    private XmlLoaderBean xmlLoaderBean;

    private XmlPathDAO xmlPathDAO = new XmlPathDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long pathId = Long.parseLong(req.getParameter("pathId"));
            XmlPath xmlPath = xmlPathDAO.findById(pathId).orElseThrow(NoSuchElementException::new);
            List<Route> routes = xmlLoaderBean.loadFromXml(xmlPath.getPath()).orElse(new LinkedList<>());
            req.getSession().setAttribute("routes", routes);
        } catch (NoSuchElementException | NumberFormatException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/view/LoadRoutesPage.jsp");
    }
}
