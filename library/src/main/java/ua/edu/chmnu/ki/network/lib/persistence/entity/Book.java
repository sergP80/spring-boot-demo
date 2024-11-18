package ua.edu.chmnu.ki.network.lib.persistence.entity;

import lombok.Data;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "library", name = "book")
public class Book {

    @Id
    @GeneratedValue(generator = "BOOK_GENERATOR")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "website")
    private String website;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    //true is available
    //false book is taken by user

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "library.BookStatus")
    private BookStatus status;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<History> history = new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Booked> booked = new ArrayList<>();
}
