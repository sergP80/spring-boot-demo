package ua.edu.chmnu.ki.network.lib.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.chmnu.ki.network.lib.error.HistoryException;
import ua.edu.chmnu.ki.network.lib.filter.dto.HistoryFilterDTO;
import ua.edu.chmnu.ki.network.lib.mapper.HistoryMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.History;
import ua.edu.chmnu.ki.network.lib.persistence.repository.HistoryRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceImplTest {
    @Mock
    private HistoryRepository HistoryRepository;

    @Mock
    private HistoryMapper HistoryMapper;

    @InjectMocks
    private HistoryServiceImpl service;

    @Mock
    private History History;

    @Mock
    private HistoryDTO HistoryDTO;

    @Mock
    private HistoryFilterDTO HistoryFilterDTO;

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void shouldSuccessGetItemById(Integer id) {
        when(HistoryRepository.findById(id)).thenReturn(Optional.of(History));
        when(HistoryMapper.mapTo(History)).thenReturn(HistoryDTO);

        assertEquals(HistoryDTO, service.getById(id));
    }


}
