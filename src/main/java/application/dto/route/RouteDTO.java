package application.dto.route;

import application.entity.City;
import application.entity.Transport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Route")
@XmlType(propOrder = {"id", "index", "departure", "destination", "departureTime", "arrivalTime", "transport" })
public class RouteDTO {

    private Long id;
    private Integer index;
    private City departure;
    private City destination;
    private String departureTime;
    private String arrivalTime;
    private Transport transport;

}
