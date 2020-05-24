package application.dto.transport;

import application.entity.Route;
import application.entity.TransportKinds;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TransportDTO {

    private Long id;
    private TransportKinds kind;
    private String name;
    private Long capacity;

    private Set<Route> routes;
}
