package application.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private Long id;
    private Integer index;
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
