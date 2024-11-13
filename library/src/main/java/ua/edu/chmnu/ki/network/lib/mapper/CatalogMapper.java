package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CatalogMapper {

    Catalog mapTo(CatalogDTO source);

    CatalogDTO mapTo(Catalog source);
}
