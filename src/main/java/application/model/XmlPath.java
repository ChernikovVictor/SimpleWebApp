package application.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class XmlPath {

    private Long id;
    private String path;

}
