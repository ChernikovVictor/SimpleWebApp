package application.bean;

import application.dto.route.RouteDTO;
import lombok.AllArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/* Класс-DTO для сохранения списка маршрутов в XML файл */
@AllArgsConstructor
@XmlRootElement(name = "RouteList")
public class RouteListXmlDTO {

    private List<RouteDTO> routes;

    public RouteListXmlDTO() {
        routes = new LinkedList<>();
    }

    @XmlElementWrapper(name = "routes")
    @XmlElements({
            @XmlElement(name = "route", type = RouteDTO.class)
    })
    public List<RouteDTO> getRoutes() {
        return routes;
    }
}
