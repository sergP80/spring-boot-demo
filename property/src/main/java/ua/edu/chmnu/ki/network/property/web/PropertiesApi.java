package ua.edu.chmnu.ki.network.property.web;

import ua.edu.chmnu.ki.network.property.filter.dto.PropertiesFilterDTO;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;
import ua.edu.chmnu.ki.network.property.web.dto.ChangeStatusDTO;
import ua.edu.chmnu.ki.network.property.web.dto.PropertiesDTO;

public interface PropertiesApi extends CrudApi<PropertiesDTO, Integer>, SearchApi<PropertiesDTO, PropertiesFilterDTO> {
    void changeStatus(Integer id, Status status);

    void changeStatus(ChangeStatusDTO changeStatus);
}
