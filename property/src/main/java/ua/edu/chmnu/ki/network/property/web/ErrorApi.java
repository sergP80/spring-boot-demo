package ua.edu.chmnu.ki.network.property.web;

import org.springframework.http.ResponseEntity;
import ua.edu.chmnu.ki.network.property.web.dto.ErrorDTO;

import javax.xml.catalog.CatalogException;


public interface ErrorApi {

    ResponseEntity<ErrorDTO> handle(CatalogException e);

    ResponseEntity<ErrorDTO> handle(Exception e);
}
