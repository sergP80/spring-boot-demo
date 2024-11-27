package ua.edu.chmnu.ki.network.property.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ua.edu.chmnu.ki.network.property.filter.dto.PropertiesFilterDTO;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;
import ua.edu.chmnu.ki.network.property.service.PropertiesService;
import ua.edu.chmnu.ki.network.property.web.dto.ChangeStatusDTO;
import ua.edu.chmnu.ki.network.property.web.dto.PageDTO;
import ua.edu.chmnu.ki.network.property.web.dto.PropertiesDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertiesApiImpl implements PropertiesApi{
    private final PropertiesService service;

    @GetMapping("/{id}")
    @Override
    public PropertiesDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @Deprecated
    @GetMapping(value = "/all")
    @Override
    public List<PropertiesDTO> getAllBy(PropertiesFilterDTO filter) {
        return service.getAllBy(filter);
    }

    @GetMapping(value = "/v1/all")
    @Override
    public List<PropertiesDTO> getAllBy(PropertiesFilterDTO filter, Sort sort) {
        return service.getAllBy(filter, sort);
    }

    @GetMapping(value = "/v2/all")
    @Override
    public PageDTO<PropertiesDTO> getAllBy(PropertiesFilterDTO filter, Pageable pageable) {
        return service.getAllBy(filter, pageable);
    }

    @PostMapping
    @Override
    public PropertiesDTO create(@RequestBody @Valid PropertiesDTO source) {
        return service.create(source);
    }

    @PutMapping("/{id}")
    @Override
    public PropertiesDTO update(@PathVariable Integer id, @RequestBody @Valid PropertiesDTO source) {
        return service.updateById(id, source);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}/status")
    @Override
    public void changeStatus(@NotNull @PathVariable(name = "id") Integer id, @NotNull @RequestParam(name = "newStatus") Status status) {
        service.changeStatus(id, status);
    }

    @PutMapping("/status")
    @Override
    public void changeStatus(@Valid @RequestBody ChangeStatusDTO changeStatus) {
        service.changeStatus(changeStatus.getId(), changeStatus.getStatus());
    }
}








