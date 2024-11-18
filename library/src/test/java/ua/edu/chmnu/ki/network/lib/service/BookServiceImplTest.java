package ua.edu.chmnu.ki.network.lib.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.chmnu.ki.network.lib.error.BookException;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;
import ua.edu.chmnu.ki.network.lib.mapper.BookMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository BookRepository;

    @Mock
    private BookMapper BookMapper;

    @InjectMocks
    private BookServiceImpl service;

    @Mock
    private Book Book;

    @Mock
    private BookDTO BookDTO;

    @Mock
    private BookFilterDTO BookFilterDTO;

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void shouldSuccessGetItemById(Integer id) {
        when(BookRepository.findById(id)).thenReturn(Optional.of(Book));
        when(BookMapper.mapTo(Book)).thenReturn(BookDTO);

        assertEquals(BookDTO, service.getById(id));
    }

}
