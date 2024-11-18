package ua.edu.chmnu.ki.network.lib.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.chmnu.ki.network.lib.error.ClientException;
import ua.edu.chmnu.ki.network.lib.filter.dto.ClientFilterDTO;
import ua.edu.chmnu.ki.network.lib.mapper.ClientMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Client;
import ua.edu.chmnu.ki.network.lib.persistence.repository.ClientRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.ClientDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private Client client;

    @Mock
    private ClientDTO clientDTO;

    @Mock
    private ClientFilterDTO ClientFilterDTO;

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void shouldSuccessGetItemById(Integer id) {
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(clientMapper.mapTo(client)).thenReturn(clientDTO);

        assertEquals(clientDTO, service.getById(id));
    }


}
