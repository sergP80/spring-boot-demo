package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.*;
import ua.edu.chmnu.ki.network.lib.persistence.entity.History;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        uses = {BookMapper.class, ClientMapper.class}
)
public interface HistoryMapper {

    History mapTo(HistoryDTO source);

    HistoryDTO mapTo(History source);

}

