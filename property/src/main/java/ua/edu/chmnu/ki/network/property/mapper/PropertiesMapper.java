package ua.edu.chmnu.ki.network.property.mapper;

import org.mapstruct.*;
import ua.edu.chmnu.ki.network.property.persistence.entity.Properties;
import ua.edu.chmnu.ki.network.property.web.dto.PropertiesDTO;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface PropertiesMapper {

    Properties mapTo(PropertiesDTO source);

    PropertiesDTO mapTo(Properties source);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Properties mapWith(PropertiesDTO source, @MappingTarget Properties target);
}

