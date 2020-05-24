package application.bean;

import application.dto.route.RouteDTO;
import application.entity.City;
import application.entity.Transport;
import lombok.AllArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/* Класс-DTO для сохранения списка маршрутов в XML файл */
@AllArgsConstructor
@XmlRootElement
@XmlSeeAlso({RouteDTO.class, Transport.class, City.class})
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
