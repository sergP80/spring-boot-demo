package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;

public interface BookApi extends ResourceApi<BookDTO, Integer>, ResourceFilterApi<BookDTO, BookFilterDTO> {
}
