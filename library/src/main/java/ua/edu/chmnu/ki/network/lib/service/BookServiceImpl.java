package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.BookException;
import ua.edu.chmnu.ki.network.lib.mapper.BookMapper;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.BookFilterDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    @Override
    public BookDTO getById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapTo)
                .orElseThrow(() -> new BookException("Book not found " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDTO> getAll() {
        try (var stream = bookRepository.streamAll()) {
            return stream.map(bookMapper::mapTo).collect(Collectors.toList());
        }

//        return bookRepository.findAll().stream().map(bookMapper::mapTo).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existById(Integer id) {
        return bookRepository.existsById(id);
    }

    @Transactional
    @Override
    public BookDTO create(BookDTO source) {
        var entity = bookMapper.mapTo(source);

        entity.setId(null);

        return bookMapper.mapTo(bookRepository.save(entity));
    }

    @Transactional
    @Override
    public BookDTO updateById(Integer id, BookDTO source) {
        return bookRepository.findById(id)
                .map(e -> bookMapper.mapWith(source, e))
                .map(bookMapper::mapTo)
                .orElseThrow(() -> new BookException("Book not found " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDTO> getAllBy(BookFilterDTO filter) {
        try (var stream = bookRepository.streamAllByFilter(filter)) {
            return stream.map(bookMapper::mapTo).collect(Collectors.toList());
        }
    }
}
