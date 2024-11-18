package ua.edu.chmnu.ki.network.lib.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.chmnu.ki.network.lib.error.BookedException;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookedFilterDTO;
import ua.edu.chmnu.ki.network.lib.mapper.BookedMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Booked;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookedRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookedServiceImplTest {
    @Mock
    private BookedRepository BookedRepository;

    @Mock
    private BookedMapper BookedMapper;

    @InjectMocks
    private BookedServiceImpl service;

    @Mock
    private Booked Booked;

    @Mock
    private BookedDTO BookedDTO;

    @Mock
    private BookedFilterDTO BookedFilterDTO;

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void shouldSuccessGetItemById(Integer id) {
        when(BookedRepository.findById(id)).thenReturn(Optional.of(Booked));
        when(BookedMapper.mapTo(Booked)).thenReturn(BookedDTO);

        assertEquals(BookedDTO, service.getById(id));
    }

}
