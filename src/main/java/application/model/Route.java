package application.model;

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
@XmlRootElement
@XmlType(propOrder = {"id", "departureId", "destinationId", "departureTime", "arrivalTime", "transportId" })
public class Route {

    private Long id;
    private Long departureId;
    private Long destinationId;
    private String departureTime;
    private String arrivalTime;
    private Long transportId;

}
