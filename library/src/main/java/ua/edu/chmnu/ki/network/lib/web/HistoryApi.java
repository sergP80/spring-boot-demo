package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.filter.dto.HistoryFilterDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;

public interface HistoryApi extends CrudApi<HistoryDTO, Integer>, SearchApi<HistoryDTO, HistoryFilterDTO> {
}
