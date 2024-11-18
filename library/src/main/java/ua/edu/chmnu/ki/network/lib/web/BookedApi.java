package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.filter.dto.BookedFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;

public interface BookedApi extends CrudApi<BookedDTO, Integer>, SearchApi<BookedDTO, BookedFilterDTO> {
}
