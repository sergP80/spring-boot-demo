package ua.edu.chmnu.ki.network.lib.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Booked;

import java.util.Optional;

@Repository
public interface BookedRepository extends JpaRepository<Booked, Integer>, JpaSpecificationExecutor<Booked> {
    Optional<Booked> findByClientIdAndBookId(Integer clientId, Integer bookId);
}
