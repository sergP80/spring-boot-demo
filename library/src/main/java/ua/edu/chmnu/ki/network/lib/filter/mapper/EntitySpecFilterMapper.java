package ua.edu.chmnu.ki.network.lib.filter.mapper;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import ua.edu.chmnu.ki.network.lib.filter.EntityFiltered;

public interface EntitySpecFilterMapper<Filter extends EntityFiltered, Entity> {

    Specification<Entity> mapTo(@NonNull Filter filter);
}
