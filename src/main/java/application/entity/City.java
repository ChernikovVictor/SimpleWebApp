package application.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private Long id;
    private String name;
    private String station;

    private Set<Route> routes = new HashSet<>();

    @Override
    public String toString() {
        return String.format("%d %s %s", id, name, station);
    }
}
