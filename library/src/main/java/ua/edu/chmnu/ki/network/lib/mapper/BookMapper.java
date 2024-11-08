package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AuthorMapper.class, CatalogMapper.class}
)
public interface BookMapper {

    Book mapTo(BookDTO source);

    BookDTO mapTo(Book source);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Book mapWith(BookDTO source, @MappingTarget Book target);
}
