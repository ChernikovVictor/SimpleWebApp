package application.dto.route;

import application.dto.city.CityDTO;
import application.dto.transport.TransportDTO;
import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
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

    @Override
    public String toString() {
        return String.format("%d %s-%s %s-%s %s", id, departure.getName(),
                destination.getName(), departureTime, arrivalTime, transport.getName());
    }

}
