package ua.edu.chmnu.ki.network.property.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;

import javax.validation.constraints.NotNull;

@Data
public class ChangeStatusDTO {

    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("status")
    private Status status;
}
