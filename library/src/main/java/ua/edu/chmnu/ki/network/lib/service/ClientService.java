package ua.edu.chmnu.ki.network.lib.service;

import ua.edu.chmnu.ki.network.lib.filter.dto.ClientFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.ClientDTO;

public interface ClientService extends CrudService<ClientDTO>, SearchService<ClientDTO, ClientFilterDTO> {
    public String bookingBook(Integer clientId, Integer bookId);
    public String returningBook(Integer clientId, Integer bookId);
}
