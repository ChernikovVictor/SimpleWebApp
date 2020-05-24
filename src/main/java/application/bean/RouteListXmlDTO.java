package application.bean;

import application.entity.Route;
import lombok.AllArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/* Класс-DTO для сохранения списка маршрутов в XML файл */
@AllArgsConstructor
@XmlRootElement
@XmlSeeAlso(Route.class)
public class RouteListXmlDTO {

    private List<Route> routes;

    public RouteListXmlDTO() {
        routes = new LinkedList<>();
    }

    @XmlElementWrapper(name = "routes")
    @XmlElements({
            @XmlElement(name = "route", type = Route.class)
    })
    public List<Route> getRoutes() {
        return routes;
    }
}
