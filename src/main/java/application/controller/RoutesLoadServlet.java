package application.controller;

import application.bean.XmlLoaderBean;
import application.dto.route.RouteDTO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/loadRoutesFromXml")
@MultipartConfig
public class RoutesLoadServlet extends HttpServlet {

    @EJB
    private XmlLoaderBean xmlLoaderBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">

        if (xmlLoaderBean.isValid(filePart.getInputStream())) {
            List<RouteDTO> routes = xmlLoaderBean.loadFromXml(filePart.getInputStream()).orElse(Collections.emptyList());
            req.getSession().setAttribute("routes", routes);
            resp.sendRedirect("/view/LoadRoutesPage.jsp");
        } else {
            req.setAttribute("message", "File is not valid");
            req.getRequestDispatcher("/view/ErrorPage.jsp").forward(req, resp);
        }
    }
}
