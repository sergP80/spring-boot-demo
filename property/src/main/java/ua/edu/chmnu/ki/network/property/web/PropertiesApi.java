package ua.edu.chmnu.ki.network.property.web;

import ua.edu.chmnu.ki.network.property.filter.dto.PropertiesFilterDTO;
import ua.edu.chmnu.ki.network.property.web.dto.PropertiesDTO;

public interface PropertiesApi extends CrudApi<PropertiesDTO, Integer>, SearchApi<PropertiesDTO, PropertiesFilterDTO> {
}
