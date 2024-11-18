package ua.edu.chmnu.ki.network.lib.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookedFilterDTO;
import ua.edu.chmnu.ki.network.lib.service.BookedService;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.PageDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library/booked")
@RequiredArgsConstructor
public class BookedApiImpl implements BookedApi {
    private final BookedService service;

    @GetMapping("/{id}")
    @Override
    public BookedDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @Deprecated
    @GetMapping(value = "/all")
    @Override
    public List<BookedDTO> getAllBy(BookedFilterDTO filter) {
        return service.getAllBy(filter);
    }

    @GetMapping(value = "/v1/all")
    @Override
    public List<BookedDTO> getAllBy(BookedFilterDTO filter, Sort sort) {
        return service.getAllBy(filter, sort);
    }

    @GetMapping(value = "/v2/all")
    @Override
    public PageDTO<BookedDTO> getAllBy(BookedFilterDTO filter, Pageable pageable) {
        return service.getAllBy(filter, pageable);
    }

    @PostMapping
    @Override
    public BookedDTO create(@RequestBody @Valid BookedDTO source) {
        return service.create(source);
    }

    @PutMapping("/{id}")
    @Override
    public BookedDTO update(@PathVariable Integer id, @RequestBody @Valid BookedDTO source) {
        return service.updateById(id, source);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
