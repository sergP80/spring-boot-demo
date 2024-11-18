package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.filter.dto.ClientFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.ClientDTO;

public interface ClientApi extends CrudApi<ClientDTO, Integer>, SearchApi<ClientDTO, ClientFilterDTO> {
}
