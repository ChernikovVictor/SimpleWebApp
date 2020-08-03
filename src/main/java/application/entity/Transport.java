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
@Table(name = "transports")
public class Transport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TransportKinds kind;

    @Column
    private String name;

    @Column
    private Long capacity;

    @OneToMany(mappedBy = "transport", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Route> routes = new HashSet<>();
}
