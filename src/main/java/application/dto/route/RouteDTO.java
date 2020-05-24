package application.dto.route;

import application.entity.City;
import application.entity.Transport;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDTO {

    private Long id;
    private City departure;
    private City destination;
    private String departureTime;
    private String arrivalTime;
    private Transport transport;

}
