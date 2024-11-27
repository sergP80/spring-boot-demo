package ua.edu.chmnu.ki.network.property.web.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Optional;

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

    private Status status;

    @JsonGetter("status")
    public Status getStatus() {
        return Optional.ofNullable(status)
                .orElse(Status.AVAILABLE);
    }

    @JsonSetter("status")
    public void setStatus(Status status) {
        this.status = status;

        if (this.status == null) {
            this.status = Status.AVAILABLE;
        }
    }
}