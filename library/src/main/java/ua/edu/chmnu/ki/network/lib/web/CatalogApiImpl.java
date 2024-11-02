package ua.edu.chmnu.ki.network.lib.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;
import ua.edu.chmnu.ki.network.lib.service.CatalogService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class CatalogApiImpl implements CatalogApi {

    private final CatalogService catalogService;

    @GetMapping("/catalog/{id}")
    @Override
    public CatalogDTO getById(@PathVariable(name = "id") Integer id) {
        return catalogService.getById(id);
    }

    @GetMapping("/catalog/all")
    @Override
    public List<CatalogDTO> getAll() {
        return catalogService.getAll();
    }

    @PostMapping("/catalog")
    @Override
    public CatalogDTO add(@Valid @RequestBody CatalogDTO catalog) {
        return catalogService.create(catalog);
    }
}
