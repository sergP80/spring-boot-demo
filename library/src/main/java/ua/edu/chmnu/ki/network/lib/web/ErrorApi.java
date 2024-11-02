package ua.edu.chmnu.ki.network.lib.web;

import org.springframework.http.ResponseEntity;
import ua.edu.chmnu.ki.network.lib.web.dto.ErrorDTO;
import ua.edu.chmnu.ki.network.lib.error.CatalogException;

public interface ErrorApi {

    ResponseEntity<ErrorDTO> handle(CatalogException e);

    ResponseEntity<ErrorDTO> handle(Exception e);
}
