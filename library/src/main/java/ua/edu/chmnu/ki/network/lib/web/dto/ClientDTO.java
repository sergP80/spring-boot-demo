package ua.edu.chmnu.ki.network.lib.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    @JsonProperty("id")
    private Integer id;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @NotBlank
    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;
}
