package application.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")
@NamedQueries({
        @NamedQuery(name = "findAllByTransportKind", query = "from Route r where r.transport.kind = :kind"),
        @NamedQuery(name = "findAllByCityName", query = "from Route r where r.departure.name = :cityName or r.destination.name = :cityName"),
        @NamedQuery(name = "findByIndex", query = "from Route r where r.index = :index")
})
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer index;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_id", nullable = false)
    private City departure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id", nullable = false)
    private City destination;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport transport;

}
