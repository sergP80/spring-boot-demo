package ua.edu.chmnu.ki.network.lib.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.chmnu.ki.network.lib.filter.dto.CatalogFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;
import ua.edu.chmnu.ki.network.lib.service.CatalogService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library/catalog")
@RequiredArgsConstructor
public class CatalogApiImpl implements CatalogApi {

    private final CatalogService service;

    @GetMapping("/{id}")
    @Override
    public CatalogDTO getById(@PathVariable(name = "id") Integer id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    @Override
    public List<CatalogDTO> getAllBy(CatalogFilterDTO filter) {
        return service.getAllBy(filter);
    }

    @PostMapping
    @Override
    public CatalogDTO create(@Valid @RequestBody CatalogDTO catalog) {
        return service.create(catalog);
    }

    @PutMapping("/{id}")
    @Override
    public CatalogDTO update(Integer id, CatalogDTO source) {
        throw new UnsupportedOperationException("Update catalog is not supported.");
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
