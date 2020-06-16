package application.dto.transport;

import application.entity.Route;
import application.entity.TransportKinds;
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
@XmlRootElement(name = "Transport")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"kind", "name", "capacity"})
public class TransportDTO {

    @XmlAttribute
    private Long id;

    private TransportKinds kind;

    private String name;

    private Long capacity;

    @XmlTransient
    private Set<Route> routes = new HashSet<>();

    @Override
    public String toString() {
        return String.format("%d %s %s %d", id, kind.name(), name, capacity);
    }
}
