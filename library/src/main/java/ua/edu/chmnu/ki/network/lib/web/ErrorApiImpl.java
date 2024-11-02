package ua.edu.chmnu.ki.network.lib.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.edu.chmnu.ki.network.lib.web.dto.ErrorDTO;
import ua.edu.chmnu.ki.network.lib.error.CatalogException;

@ControllerAdvice
public class ErrorApiImpl implements ErrorApi {

    private static final Logger LOGGER = LoggerFactory.getLogger("CatalogErrorLogger");

    @ExceptionHandler(CatalogException.class)
    @Override
    public ResponseEntity<ErrorDTO> handle(CatalogException e) {
        return handle(400, e);
    }

    @ExceptionHandler(Exception.class)
    @Override
    public ResponseEntity<ErrorDTO> handle(Exception e) {
        return handle(500, e);
    }

    private ResponseEntity<ErrorDTO> handle(Integer status, Exception e) {
        LOGGER.error(e.getMessage(), e);

        var payload = ErrorDTO.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity
                .status(status)
                .body(payload);
    }
}
