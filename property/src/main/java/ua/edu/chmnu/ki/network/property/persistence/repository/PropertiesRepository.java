package ua.edu.chmnu.ki.network.property.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.edu.chmnu.ki.network.property.persistence.entity.Properties;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Integer>, JpaSpecificationExecutor<Properties> {

}
