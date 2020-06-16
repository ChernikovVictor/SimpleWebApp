package application.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum TransportKinds {

    @XmlEnumValue("Train")
    TRAIN,

    @XmlEnumValue("Plane")
    PLANE
}
