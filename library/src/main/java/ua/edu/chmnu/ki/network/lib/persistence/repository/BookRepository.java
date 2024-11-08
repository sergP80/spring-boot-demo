package ua.edu.chmnu.ki.network.lib.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.web.dto.BookFilterDTO;

import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(
            "SELECT book FROM Book book JOIN FETCH Author author"
    )
    Stream<Book> streamAll();

    @Query(
            """
                            SELECT book FROM Book book
                            WHERE (:#{#filter.id} IS NULL OR book.id = :#{#filter.id})
                             AND (:#{#filter.title} IS NULL OR book.title like %:#{#filter.title}%)
                             AND (:#{#filter.author} IS NULL OR book.author.name like %:#{#filter.author}%)
                    """
    )
    Stream<Book> streamAllByFilter(@Param("filter") BookFilterDTO filter);
}
