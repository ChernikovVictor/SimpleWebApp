package application.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"id", "kind", "name", "capacity"})
public class Transport {

    private Long id;
    private TransportKinds kind;
    private String name;
    private Long capacity;

    private Set<Route> routes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportKinds getKind() {
        return kind;
    }

    public void setKind(TransportKinds kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    @XmlTransient
    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %d", id, kind.name(), name, capacity);
    }
}
