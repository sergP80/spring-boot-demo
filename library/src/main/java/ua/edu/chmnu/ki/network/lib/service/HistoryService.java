package ua.edu.chmnu.ki.network.lib.service;

import ua.edu.chmnu.ki.network.lib.filter.dto.HistoryFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;

public interface HistoryService extends CrudService<HistoryDTO>, SearchService<HistoryDTO, HistoryFilterDTO> {
}
