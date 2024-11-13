package ua.edu.chmnu.ki.network.lib.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;
import ua.edu.chmnu.ki.network.lib.service.BookService;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library/books")
@RequiredArgsConstructor
public class BookApiImpl implements BookApi {

    private final BookService service;

    @GetMapping("/{id}")
    @Override
    public BookDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping(value = "/all")
    @Override
    public List<BookDTO> getAllBy(BookFilterDTO filter) {
        return service.getAllBy(filter);
    }

    @PostMapping
    @Override
    public BookDTO create(@RequestBody @Valid BookDTO source) {
        return service.create(source);
    }

    @PutMapping("/{id}")
    @Override
    public BookDTO update(@PathVariable Integer id, @RequestBody @Valid BookDTO source) {
        return service.updateById(id, source);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
