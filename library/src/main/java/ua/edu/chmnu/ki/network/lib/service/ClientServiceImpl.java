package ua.edu.chmnu.ki.network.lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.lib.error.BookException;
import ua.edu.chmnu.ki.network.lib.error.BookedException;
import ua.edu.chmnu.ki.network.lib.error.ClientException;
import ua.edu.chmnu.ki.network.lib.error.HistoryException;
import ua.edu.chmnu.ki.network.lib.filter.dto.ClientFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.mapper.BookMapper;
import ua.edu.chmnu.ki.network.lib.mapper.BookedMapper;
import ua.edu.chmnu.ki.network.lib.mapper.ClientMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Client;
import ua.edu.chmnu.ki.network.lib.persistence.entity.History;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookRepository;
import ua.edu.chmnu.ki.network.lib.persistence.repository.BookedRepository;
import ua.edu.chmnu.ki.network.lib.persistence.repository.ClientRepository;
import ua.edu.chmnu.ki.network.lib.persistence.repository.HistoryRepository;
import ua.edu.chmnu.ki.network.lib.web.dto.BookDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.BookedDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.ClientDTO;
import ua.edu.chmnu.ki.network.lib.web.dto.HistoryDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final BookRepository bookRepository;

    private final ClientMapper clientMapper;

    private final EntitySpecFilterMapper<ClientFilterDTO, Client> clientSpecFilterMapper;

    private final BookedService bookedService;

    private final BookMapper bookMapper;
    private final BookedRepository bookedRepository;

    private final HistoryService historyService;
    private final HistoryRepository historyRepository;
    private final BookedMapper bookedMapper;


    @Transactional(readOnly = true)
    @Override
    public ClientDTO getById(Integer id) {

        return clientRepository.findById(id)
                .map(clientMapper::mapTo)
                .orElseThrow(() -> new ClientException("Client with id " + id + " not found"));
    }


    @Transactional(readOnly = true)
    @Override
    public List<ClientDTO> getAllBy(ClientFilterDTO filter) {
        return getAllBy(filter, Sort.by(Sort.Order.asc("id")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientDTO> getAllBy(ClientFilterDTO filter, Sort sort) {
        var specification = clientSpecFilterMapper.mapTo(filter);

        return clientRepository.findAll(specification, sort).stream()
                .map(clientMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existById(Integer id) {
        return clientRepository.existsById(id);
    }

    @Transactional
    @Override
    public ClientDTO create(ClientDTO newClient) {
        if (this.existById(newClient.getId())) {
            throw new ClientException("Client with id " + newClient.getId() + " already exists");
        }

        Client client = clientMapper.mapTo(newClient);

        client.setId(null);

        clientRepository.save(client);

        return clientMapper.mapTo(client);
    }

    @Transactional
    @Override
    public ClientDTO updateById(Integer id, ClientDTO client) {
        if (this.existById(id)) {
            throw new ClientException("Client with id " + id + " already exists");
        }

        Client clientEntity = clientMapper.mapTo(client);

        client.setId(id);

        clientRepository.save(clientEntity);

        return clientMapper.mapTo(clientEntity);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!existById(id)) {
            throw new ClientException("Client with id " + id + " does not exist");
        }

        clientRepository.deleteById(id);
    }

    @Transactional
    public String bookingBook(Integer clientId, Integer bookId) {
        if (clientId == null || bookId == null) {
            throw new IllegalArgumentException("Client ID and Book ID must be provided");
        }
        // Find the book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ClientException("Book with id " + bookId + " not found"));

        // Ensure the book is available
        if (!book.getStatus().equals(BookStatus.AVAILABLE)) {
            throw new ClientException("Book with id " + bookId + " is not available for booking");
        }

        // Find the client
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientException("Client with id " + clientId + " not found"));

        BookedDTO bookedDTO = new BookedDTO();
        bookedDTO.setBook(bookMapper.mapTo(book));
        bookedDTO.setClient(clientMapper.mapTo(client));
        bookedDTO.setBorrowedAt(LocalDate.now());
        bookedDTO = bookedService.create(bookedDTO);
        System.out.println(bookedDTO);
        book.setStatus(BookStatus.BORROWED);
        bookRepository.save(book);
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setBook(bookMapper.mapTo(book));
        historyDTO.setClient(clientMapper.mapTo(client));
        historyDTO.setStatus(book.getStatus());
        historyDTO.setBorrowedAt(LocalDate.now());
        historyDTO = historyService.create(historyDTO);
        System.out.println(historyDTO);

        return String.format("Book with ID %d successfully booked by client ID %d.", bookId, clientId);
    }

    @Transactional
    public String returningBook(Integer clientId, Integer bookId) {
        // Retrieve the booking record
        var booked = bookedRepository.findByClientIdAndBookId(clientId, bookId)
                .orElseThrow(() -> new BookedException("Booking record not found for clientId: " + clientId + " and bookId: " + bookId));

        // Remove the booking
        bookedService.deleteById(booked.getId());

        // Update the book status to AVAILABLE
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book with id " + bookId + " not found"));
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);

        History history = historyRepository.findByClientIdAndBookIdAndStatus(clientId, bookId, BookStatus.BORROWED)
                .orElseThrow(() -> new HistoryException("Booking record not found for clientId: " + clientId + " and bookId: " + bookId + " with Status BORROWED"));;
        history.setReturnedAt(LocalDate.now());
        history.setStatus(BookStatus.AVAILABLE);
        historyRepository.save(history);

        return String.format("Book with ID %d successfully returned by client ID %d.", bookId, clientId);
    }
}
