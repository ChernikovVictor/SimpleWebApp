package application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transport {

    private Long id;
    private TransportKinds kind;
    private String name;
    private Long capacity;

}
