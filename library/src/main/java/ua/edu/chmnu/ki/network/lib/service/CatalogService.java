package ua.edu.chmnu.ki.network.lib.service;

import ua.edu.chmnu.ki.network.lib.filter.dto.CatalogFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

public interface CatalogService extends CrudService<CatalogDTO>, SearchService<CatalogDTO, CatalogFilterDTO> {
}
