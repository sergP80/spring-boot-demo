package ua.edu.chmnu.ki.network.lib.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.edu.chmnu.ki.network.lib.persistence.entity.History;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer>, JpaSpecificationExecutor<History> {
    Optional<History> findByClientIdAndBookIdAndStatus(Integer clientId, Integer bookId, BookStatus status);
}
