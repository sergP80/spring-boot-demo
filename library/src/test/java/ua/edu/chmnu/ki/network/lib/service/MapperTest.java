package ua.edu.chmnu.ki.network.lib.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.chmnu.ki.network.lib.mapper.*;

@ExtendWith(MockitoExtension.class)
public class MapperTest {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookedMapper bookedMapper;

    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    CatalogMapper catalogMapper;

}
