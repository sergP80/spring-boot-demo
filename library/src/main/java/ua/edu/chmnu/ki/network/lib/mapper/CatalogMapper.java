package ua.edu.chmnu.ki.network.lib.mapper;

import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

public interface CatalogMapper {

    Catalog mapTo(CatalogDTO source);

    CatalogDTO mapTo(Catalog source);
}
