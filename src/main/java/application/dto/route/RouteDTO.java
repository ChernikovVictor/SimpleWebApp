package application.dto.route;

import application.dto.city.CityDTO;
import application.dto.transport.TransportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Route")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"departure", "destination", "departureTime", "arrivalTime", "transport" })
public class RouteDTO {

    @XmlTransient
    private Long id;

    @XmlAttribute
    private Integer index;

    private CityDTO departure;
    private CityDTO destination;

    private String departureTime;
    private String arrivalTime;

    private TransportDTO transport;

}
