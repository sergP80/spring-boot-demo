package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CatalogMapper {

    Catalog mapTo(CatalogDTO source);

    CatalogDTO mapTo(Catalog source);
}
