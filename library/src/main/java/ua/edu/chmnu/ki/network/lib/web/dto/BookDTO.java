package ua.edu.chmnu.ki.network.lib.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    @JsonProperty("id")
    private Integer id;

    @NotBlank
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @Valid
    @JsonProperty("author")
    private AuthorDTO author;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("pages")
    private Integer pages;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonProperty("issueDate")
    private LocalDate issueDate;

    @JsonProperty("website")
    private String website;

    @JsonProperty("catalog")
    private CatalogDTO catalog;
}
