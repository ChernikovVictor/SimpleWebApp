package application.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transport {

    private Long id;
    private TransportKinds kind;
    private String name;
    private Long capacity;

    private Set<Route> routes = new HashSet<>();

    @Override
    public String toString() {
        return String.format("%d %s %s %d", id, kind.name(), name, capacity);
    }
}
