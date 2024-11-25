package ua.edu.chmnu.ki.network.property.service;


import ua.edu.chmnu.ki.network.property.filter.dto.PropertiesFilterDTO;
import ua.edu.chmnu.ki.network.property.web.dto.PropertiesDTO;

public interface PropertiesService extends CrudService<PropertiesDTO>, SearchService<PropertiesDTO, PropertiesFilterDTO> {
    public String setStatusSold(Integer propertiesId);
}

