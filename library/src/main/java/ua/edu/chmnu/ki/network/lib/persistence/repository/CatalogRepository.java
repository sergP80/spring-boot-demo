package ua.edu.chmnu.ki.network.lib.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
