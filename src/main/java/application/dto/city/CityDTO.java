package application.dto.city;

import application.entity.Route;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CityDTO {

    private Long id;
    private String name;
    private String station;

    private Set<Route> routes;
}
