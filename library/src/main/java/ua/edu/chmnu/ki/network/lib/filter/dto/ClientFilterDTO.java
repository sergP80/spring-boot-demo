package ua.edu.chmnu.ki.network.lib.filter.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;

@Data
public class ClientFilterDTO implements EntityFilter {

    private String name;

    private String search;

    private Sort sort;

    private String address;

    private String email;

    private String phone;

    public boolean hasSearch() {
        return search != null && !search.isBlank();
    }

    public boolean hasName() {
        return name != null && !name.isBlank();
    }

    public Sort getSort() {
        return sort == null ? Sort.by(Sort.Direction.ASC, "id") : sort;
    }

    public boolean hasAddress() {
        return address != null && !address.isBlank();
    }

    public boolean hasEmail() {
        return email != null && !email.isBlank();
    }

    public boolean hasPhone() {
        return phone != null && !phone.isBlank();
    }

}
