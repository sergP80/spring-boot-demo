package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

import java.util.List;

public interface CatalogApi {

    CatalogDTO getById(Integer id);

    List<CatalogDTO> getAll();

    CatalogDTO add(CatalogDTO catalog);
}
