package ua.edu.chmnu.ki.network.hotel.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.URL;

@Data
@Entity
@Table(schema = "core", name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_GENERATOR")
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private URL website;

    private URL logoUrl;
}
