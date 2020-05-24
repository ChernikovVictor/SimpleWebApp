package application.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@XmlRootElement
//@XmlType(propOrder = {"id", "departureId", "destinationId", "departureTime", "arrivalTime", "transportId" })
public class Route {

    private Long id;
    private City departure;
    private City destination;
    private String departureTime;
    private String arrivalTime;
    private Transport transport;

    @Override
    public String toString() {
        return String.format("%d %s-%s %s-%s %s", id, departure.getName(),
                destination.getName(), departureTime, arrivalTime, transport.getName());
    }

}
