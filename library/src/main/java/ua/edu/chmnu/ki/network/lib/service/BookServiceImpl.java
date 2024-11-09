package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.BookException;
import ua.edu.chmnu.ki.network.lib.mapper.BookMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookRepository;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final EntitySpecFilterMapper<BookFilterDTO, Book> bookSpecFilterMapper;

    @Transactional(readOnly = true)
    @Override
    public BookDTO getById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapTo)
                .orElseThrow(() -> new BookException("Book not found " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDTO> getAllBy(BookFilterDTO filter) {
        var specification = bookSpecFilterMapper.mapTo(filter);

        return bookRepository.findAll(specification, filter.getSort()).stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
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

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!existById(id)) {
            throw new BookException("Book not found " + id);
        }

        bookRepository.deleteById(id);
    }
}
