package ua.edu.chmnu.ki.network.lib.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "library", name = "booked")
public class Booked {
    @Id
    @GeneratedValue(generator = "BOOKED_GENERATOR")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrowed_at")
    private LocalDate borrowedAt;
}
