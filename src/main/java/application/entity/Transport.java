package application.entity;

import lombok.*;

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

    private Set<Route> routes;
}
