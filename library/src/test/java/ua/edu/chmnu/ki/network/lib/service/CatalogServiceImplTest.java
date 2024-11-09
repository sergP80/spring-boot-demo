package ua.edu.chmnu.ki.network.lib.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.chmnu.ki.network.lib.error.CatalogException;
import ua.edu.chmnu.ki.network.lib.filter.dto.CatalogFilterDTO;
import ua.edu.chmnu.ki.network.lib.mapper.CatalogMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;
import ua.edu.chmnu.ki.network.lib.persistence.repository.CatalogRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.CatalogDTO;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceImplTest {

    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private CatalogMapper catalogMapper;

    @InjectMocks
    private CatalogServiceImpl service;

    @Mock
    private Catalog catalog;

    @Mock
    private CatalogDTO catalogDTO;

    @Mock
    private CatalogFilterDTO catalogFilterDTO;

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void shouldSuccessGetItemById(Integer id) {
        when(catalogRepository.findById(id)).thenReturn(Optional.of(catalog));
        when(catalogMapper.mapTo(catalog)).thenReturn(catalogDTO);

        assertEquals(catalogDTO, service.getById(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void shouldFailGetItemByIdWhenItIsNotExist(Integer id) {
        when(catalogRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CatalogException.class, () -> service.getById(id));
    }

    @Test
    void shouldGetAllCatalogs() {
        when(catalogRepository.findAll()).thenReturn(List.of(catalog));
        when(catalogMapper.mapTo(catalog)).thenReturn(catalogDTO);

        var result = service.getAllBy(catalogFilterDTO);

        assertEquals(1, result.size());
        assertEquals(catalogDTO, result.get(0));
    }
}