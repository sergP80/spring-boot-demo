package ua.edu.chmnu.ki.network.property.persistence.entity;

import lombok.Data;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;

@Data
@Entity
@Table(schema = "property", name = "properties")
public class Properties {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPERTY_GENERATOR")
    private Integer id;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "type")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "property.Status")
    private Status status;
}
