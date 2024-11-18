package ua.edu.chmnu.ki.network.lib.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.edu.chmnu.ki.network.lib.filter.dto.ClientFilterDTO;
import ua.edu.chmnu.ki.network.lib.service.ClientService;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.ClientDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library/client")
@RequiredArgsConstructor
public class ClientApiImpl implements ClientApi {

    private final ClientService service;

    @GetMapping("/{id}")
    @Override
    public ClientDTO getById(@PathVariable(name = "id") Integer id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    @Override
    public List<ClientDTO> getAllBy(ClientFilterDTO filter) {
        return service.getAllBy(filter);
    }

    @PostMapping
    @Override
    public ClientDTO create(@Valid @RequestBody ClientDTO catalog) {
        return service.create(catalog);
    }

    @PutMapping("/{id}")
    @Override
    public ClientDTO update(@PathVariable Integer id, @RequestBody @Valid  ClientDTO source) {
        return service.updateById(id, source);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PostMapping("/booking")
    public String booking(@RequestParam(name = "clientId") Integer clientId,
                        @RequestParam(name = "bookId") Integer bookId) {
        return service.bookingBook(clientId, bookId);
    }

    @PostMapping("/returning")
    public String returning(@RequestParam(name = "clientId") Integer clientId,
                          @RequestParam(name = "bookId") Integer bookId) {
        return service.returningBook(clientId, bookId);
    }
}
