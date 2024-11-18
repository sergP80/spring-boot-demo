package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.*;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Booked;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        uses = {AuthorMapper.class, CatalogMapper.class}
)
public interface BookedMapper {

    Booked mapTo(BookedDTO source);

    BookedDTO mapTo(Booked source);
}
