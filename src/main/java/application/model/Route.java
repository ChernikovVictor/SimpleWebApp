package application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Route {

    private Long id;
    private Long departureId;
    private Long destinationId;
    private String departureTime;
    private String arrivalTime;
    private Long transportId;

}
