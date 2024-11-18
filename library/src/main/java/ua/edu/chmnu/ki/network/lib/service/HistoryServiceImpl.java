package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.ClientException;
import ua.edu.chmnu.ki.network.lib.error.HistoryException;
import ua.edu.chmnu.ki.network.lib.filter.dto.HistoryFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.mapper.HistoryMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.History;
import ua.edu.chmnu.ki.network.lib.persistence.repository.HistoryRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final HistoryMapper historyMapper;

    private final EntitySpecFilterMapper<HistoryFilterDTO, History> historySpecFilterMapper;

    @Transactional(readOnly = true)
    @Override
    public HistoryDTO getById(Integer id) {

        return historyRepository.findById(id)
                .map(historyMapper::mapTo)
                .orElseThrow(() -> new ClientException("Transaction with id " + id + " not found"));
    }


    @Transactional(readOnly = true)
    @Override
    public List<HistoryDTO> getAllBy(HistoryFilterDTO filter) {
        return getAllBy(filter, Sort.by(Sort.Order.asc("id")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<HistoryDTO> getAllBy(HistoryFilterDTO filter, Sort sort) {
        var specification = historySpecFilterMapper.mapTo(filter);

        return historyRepository.findAll(specification, sort).stream()
                .map(historyMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existById(Integer id) {
        return historyRepository.existsById(id);
    }

    @Transactional
    @Override
    public HistoryDTO create(HistoryDTO source) {
        var entity = historyMapper.mapTo(source);

        entity.setId(null);

        return historyMapper.mapTo(historyRepository.save(entity));
    }

    @Transactional
    @Override
    public HistoryDTO updateById(Integer id, HistoryDTO history) {
        if (this.existById(id)) {
            throw new HistoryException("Transaction with id " + id + " already exists");
        }

        History historyEntity = historyMapper.mapTo(history);

        history.setId(id);

        historyRepository.save(historyEntity);

        return historyMapper.mapTo(historyEntity);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!existById(id)) {
            throw new HistoryException("Transaction with id " + id + " does not exist");
        }

        historyRepository.deleteById(id);
    }
}
