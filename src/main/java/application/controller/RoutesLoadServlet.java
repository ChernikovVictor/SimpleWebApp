package application.controller;

import application.bean.XmlLoaderBean;
import application.dao.XmlPathDAO;
import application.exception.NoSuchElementException;
import application.entity.Route;
import application.entity.XmlPath;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/loadRoutesFromXml")
public class RoutesLoadServlet extends HttpServlet {

    @EJB
    private XmlLoaderBean xmlLoaderBean;

    private final XmlPathDAO xmlPathDAO = new XmlPathDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long pathId = Long.parseLong(req.getParameter("pathId"));
            XmlPath xmlPath = xmlPathDAO.findById(pathId).orElseThrow(NoSuchElementException::new);

            if (!xmlLoaderBean.isValid(new File(xmlPath.getPath()))) {
                throw new ValidationException("file is not valid!");
            }

            List<Route> routes = xmlLoaderBean.loadFromXml(xmlPath.getPath()).orElse(new LinkedList<>());
            req.getSession().setAttribute("routes", routes);
            resp.sendRedirect("/view/LoadRoutesPage.jsp");

        } catch (NoSuchElementException | NumberFormatException | ValidationException e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/view/ErrorPage.jsp").forward(req, resp);
        }
    }
}
