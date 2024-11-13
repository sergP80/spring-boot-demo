package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Author;
import ua.edu.chmnu.ki.network.lib.web.dto.AuthorDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AuthorMapper {

    @Mappings({
            @Mapping(target = "name", ignore = true)
    })
    Author mapTo(AuthorDTO source);

    AuthorDTO mapTo(Author source);
}
