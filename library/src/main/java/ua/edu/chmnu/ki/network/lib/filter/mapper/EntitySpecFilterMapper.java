package ua.edu.chmnu.ki.network.lib.filter.mapper;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;

public interface EntitySpecFilterMapper<Filter extends EntityFilter, Entity> {

    Specification<Entity> mapTo(@NonNull Filter filter);
}
