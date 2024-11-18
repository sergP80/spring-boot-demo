package ua.edu.chmnu.ki.network.lib.service;


import ua.edu.chmnu.ki.network.lib.filter.dto.BookedFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;

public interface BookedService extends CrudService<BookedDTO>, SearchService<BookedDTO, BookedFilterDTO> {
}

