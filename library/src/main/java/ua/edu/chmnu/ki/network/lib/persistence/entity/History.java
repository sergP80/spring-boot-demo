package ua.edu.chmnu.ki.network.lib.persistence.entity;

import lombok.Data;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "library", name = "history")
public class History {
    @Id
    @GeneratedValue(generator = "HISTORY_GENERATOR")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //true is available
    //false book is taken by user
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "library.BookStatus")
    private BookStatus status;

    @Column(name = "borrowed_at")
    private LocalDate borrowedAt;

    @Column(name = "returned_at")
    private LocalDate returnedAt;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
