package ua.edu.chmnu.ki.network.lib.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthorDTO {

    @NotNull
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
