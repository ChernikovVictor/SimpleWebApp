package application.dto.city;

import application.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Data
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
    private Set<Route> routes = new HashSet<>();

    @Override
    public String toString() {
        return String.format("%d %s %s", id, name, station);
    }
}
