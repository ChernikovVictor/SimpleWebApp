package application.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String station;

    // Множество, хранящее маршруты, где данный город в поле "departure"
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "departure")
    private Set<Route> routesWithDeparture = new HashSet<>();

    // Множество, хранящее маршруты, где данный город в поле "destination"
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "destination")
    private Set<Route> routesWithDestination = new HashSet<>();
}
