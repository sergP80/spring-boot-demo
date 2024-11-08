package ua.edu.chmnu.ki.network.lib.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookFilterDTO {

    private Integer id;

    private String title;

    private String description;

    private String author;

    private BigDecimal price;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer pages;

    private String search;
}
