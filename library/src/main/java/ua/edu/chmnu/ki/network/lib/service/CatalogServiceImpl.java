package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.CatalogException;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.dto.CatalogFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.mapper.CatalogMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.persistence.repository.CatalogRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    private final CatalogMapper catalogMapper;

    private final EntitySpecFilterMapper<CatalogFilterDTO, Catalog> catalogSpecFilterMapper;

    @Transactional(readOnly = true)
    @Override
    public CatalogDTO getById(Integer id) {

        return catalogRepository.findById(id)
                .map(catalogMapper::mapTo)
                .orElseThrow(() -> new CatalogException("Item with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CatalogDTO> getAllBy(CatalogFilterDTO filter) {
        return getAllBy(filter, Sort.by(Sort.Order.asc("id")));
    }


    @Transactional(readOnly = true)
    @Override
    public List<CatalogDTO> getAllBy(CatalogFilterDTO filter, Sort sort) {
        var specification = catalogSpecFilterMapper.mapTo(filter);

        return catalogRepository.findAll(specification, sort).stream()
                .map(catalogMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existById(Integer id) {
        return catalogRepository.existsById(id);
    }

    @Transactional
    @Override
    public CatalogDTO create(CatalogDTO newCatalog) {
        if (this.existById(newCatalog.getId())) {
            throw new CatalogException("Catalog with id " + newCatalog.getId() + " already exists");
        }

        Catalog catalog = catalogMapper.mapTo(newCatalog);

        catalog.setId(null);

        catalogRepository.save(catalog);

        return catalogMapper.mapTo(catalog);
    }

    @Transactional
    @Override
    public CatalogDTO updateById(Integer id, CatalogDTO catalog) {
        if (this.existById(id)) {
            throw new CatalogException("Catalog with id " + id + " already exists");
        }

        Catalog catalogEntity = catalogMapper.mapTo(catalog);

        catalog.setId(id);

        catalogRepository.save(catalogEntity);

        return catalogMapper.mapTo(catalogEntity);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!existById(id)) {
            throw new CatalogException("Catalog with id " + id + " does not exist");
        }

        catalogRepository.deleteById(id);
    }
}
