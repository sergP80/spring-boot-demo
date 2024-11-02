package ua.edu.chmnu.ki.network.lib.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "library", name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(generator = "CATALOG_GENERATOR")
    private Integer id;

    @Column(name = "index")
    private String index;

    @Column(name = "name")
    private String name;
}
