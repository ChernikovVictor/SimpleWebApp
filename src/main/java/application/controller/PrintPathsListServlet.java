package application.controller;

import application.dao.XmlPathDAO;
import application.entity.XmlPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/pathsComboBox")
public class PrintPathsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<XmlPath> paths = (new XmlPathDAO().findAll());
        String optionsHTML = paths.stream().map(this::pathToOptionHtml).collect(Collectors.joining());
        resp.getWriter().write(optionsHTML);

    }

    private String pathToOptionHtml(XmlPath path) {
        return String.format("<option value=\"%d\">%s", path.getId(), path.getPath());
    }
}
