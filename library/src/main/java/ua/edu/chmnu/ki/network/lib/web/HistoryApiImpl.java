package ua.edu.chmnu.ki.network.lib.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ua.edu.chmnu.ki.network.lib.filter.dto.HistoryFilterDTO;
import ua.edu.chmnu.ki.network.lib.service.HistoryService;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.PageDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library/history")
@RequiredArgsConstructor
public class HistoryApiImpl implements HistoryApi {
    private final HistoryService service;

    @GetMapping("/{id}")
    @Override
    public HistoryDTO getById(@PathVariable(name = "id") Integer id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    @Override
    public List<HistoryDTO> getAllBy(HistoryFilterDTO filter) {
        return service.getAllBy(filter);
    }

    @GetMapping(value = "/v1/all")
    @Override
    public List<HistoryDTO> getAllBy(HistoryFilterDTO filter, Sort sort) {
        return service.getAllBy(filter, sort);
    }

    @GetMapping(value = "/v2/all")
    @Override
    public PageDTO<HistoryDTO> getAllBy(HistoryFilterDTO filter, Pageable pageable) {
        return service.getAllBy(filter, pageable);
    }

    @PostMapping
    @Override
    public HistoryDTO create(@Valid @RequestBody HistoryDTO catalog) {
        return service.create(catalog);
    }

    @PutMapping("/{id}")
    @Override
    public HistoryDTO update(@PathVariable Integer id, @RequestBody @Valid  HistoryDTO source) {
        return service.updateById(id, source);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
