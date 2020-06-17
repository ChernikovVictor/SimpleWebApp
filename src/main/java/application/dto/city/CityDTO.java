package application.dto.city;

import application.entity.Route;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "City")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CityDTO {

    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String station;

    @XmlTransient
    private Set<Route> routesWithDeparture = new HashSet<>();

    @XmlTransient
    private Set<Route> routesWithDestination = new HashSet<>();

    @Override
    public String toString() {
        return String.format("%d %s %s", id, name, station);
    }
}
