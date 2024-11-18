package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.BookException;
import ua.edu.chmnu.ki.network.lib.error.BookedException;
import ua.edu.chmnu.ki.network.lib.error.CatalogException;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookedFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.mapper.BookedMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Booked;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookedRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookedServiceImpl implements BookedService {

    private final BookedRepository bookedRepository;

    private final BookedMapper bookedMapper;

    private final EntitySpecFilterMapper<BookedFilterDTO, Booked> bookedSpecFilterMapper;

    @Transactional(readOnly = true)
    @Override
    public BookedDTO getById(Integer id) {
        return bookedRepository.findById(id)
                .map(bookedMapper::mapTo)
                .orElseThrow(() -> new BookException("Taken book not found " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookedDTO> getAllBy(BookedFilterDTO filter) {
        return getAllBy(filter, Sort.by(Sort.Order.asc("id")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookedDTO> getAllBy(BookedFilterDTO filter, Sort sort) {
        var specification = bookedSpecFilterMapper.mapTo(filter);

        return bookedRepository.findAll(specification, sort).stream()
                .map(bookedMapper::mapTo)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    @Override
    public PageDTO<BookedDTO> getAllBy(BookedFilterDTO filter, Pageable pageable) {
        var specification = bookedSpecFilterMapper.mapTo(filter);

        return PageDTO.of(bookedRepository.findAll(specification, pageable), bookedMapper::mapTo);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existById(Integer id) {
        return bookedRepository.existsById(id);
    }

    @Transactional
    @Override
    public BookedDTO create(BookedDTO source) {
        var entity = bookedMapper.mapTo(source);

        entity.setId(null);
        entity.getBook().setStatus(BookStatus.BORROWED);

        return bookedMapper.mapTo(bookedRepository.save(entity));
    }

    @Transactional
    @Override
    public BookedDTO updateById(Integer id, BookedDTO catalog) {
        if (this.existById(id)) {
            throw new CatalogException("Catalog with id " + id + " already exists");
        }

        Booked bookedEntity = bookedMapper.mapTo(catalog);

        catalog.setId(id);

        bookedRepository.save(bookedEntity);

        return bookedMapper.mapTo(bookedEntity);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        Booked bookedEntity = bookedRepository.findById(id)
                .orElseThrow(() -> new BookedException("Taken book not found " + id));

        bookedEntity.getBook().setStatus(BookStatus.AVAILABLE);
        bookedRepository.save(bookedEntity);
        bookedRepository.deleteById(id);
    }
}
