package ua.edu.chmnu.ki.network.lib.mapper;

import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

@Component
public class CatalogMapperImpl implements CatalogMapper {

    @Override
    public Catalog mapTo(CatalogDTO source) {
        Catalog catalog = new Catalog();

        catalog.setId(source.getId());

        catalog.setName(source.getName());

        catalog.setIndex(source.getIndex());

        return catalog;
    }

    @Override
    public CatalogDTO mapTo(Catalog source) {
        return CatalogDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .index(source.getIndex())
                .build();
    }
}
