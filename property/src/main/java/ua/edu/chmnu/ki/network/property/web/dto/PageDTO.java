package ua.edu.chmnu.ki.network.property.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class PageDTO<T> {

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("records")
    private List<T> records;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class Meta {
        private long total;

        private long size;

        private long page;

        private boolean isFirst;

        private boolean isLast;

        public static Meta of(Page<?> source) {

            return Meta.builder()
                    .page(source.getNumber() + 1)
                    .size(source.getSize())
                    .total(source.getTotalElements())
                    .isFirst(source.isFirst())
                    .isLast(source.isLast())
                    .build();
        }
    }

    public static <S, T> PageDTO<T> of(Page<S> source, Function<S, T> mapper) {
        return PageDTO.<T>builder()
                .meta(Meta.of(source))
                .records(source.getContent().stream().map(mapper).collect(Collectors.toList()))
                .build();
    }

    public static <T> PageDTO<T> empty() {
        return PageDTO.<T>builder()
                .meta(new Meta())
                .records(Collections.emptyList())
                .build();
    }
}
