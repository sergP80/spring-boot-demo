package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.filter.dto.CatalogFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

public interface CatalogApi extends CrudApi<CatalogDTO, Integer>, SearchApi<CatalogDTO, CatalogFilterDTO> {
}
