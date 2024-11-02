package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.CatalogException;
import ua.edu.chmnu.ki.network.lib.mapper.CatalogMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.persistence.repository.CatalogRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    private final CatalogMapper catalogMapper;

    @Transactional(readOnly = true)
    @Override
    public CatalogDTO getById(Integer id) {

        return catalogRepository.findById(id)
                .map(catalogMapper::mapTo)
                .orElseThrow(() -> new CatalogException("Item with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CatalogDTO> getAll() {
        return catalogRepository.findAll().stream()
                .map(catalogMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existById(Integer id) {
        return catalogRepository.existsById(id);
    }

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
}
