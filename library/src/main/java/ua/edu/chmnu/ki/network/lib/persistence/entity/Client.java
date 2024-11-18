package ua.edu.chmnu.ki.network.lib.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "library", name = "client")
public class Client {
    @Id
    @GeneratedValue(generator = "CLIENT_GENERATOR")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<History> history = new ArrayList<>();

    //add column available
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Booked> booked = new ArrayList<>();
}
