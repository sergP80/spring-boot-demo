package ua.edu.chmnu.ki.network.property.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertiesDTO {
    @JsonProperty("id")
    private Integer id;

    @NotBlank
    @JsonProperty("location")
    private String location;

    @JsonProperty("type")
    private String type;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("status")
    private Status status;
}