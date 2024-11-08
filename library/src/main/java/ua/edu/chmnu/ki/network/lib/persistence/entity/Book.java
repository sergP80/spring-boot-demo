package ua.edu.chmnu.ki.network.lib.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

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
}
