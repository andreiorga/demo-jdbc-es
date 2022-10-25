package ro.esolutions.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericList<T> {
    final List<T> items;

    final long totalCount;

    public static <T> GenericList<T> of(Page<T> page) {
        return new GenericList<>(page.getContent(), page.getTotalElements());
    }

    public static <T> GenericList<T> of(List<T> list) {
        return new GenericList<>(list, list.size());
    }

    public static <T> GenericList<T> of(List<T> list, long totalCount) {
        return new GenericList<>(list, totalCount);
    }

    public void forEach(Consumer<? super T> c) {
        items.forEach(c);
    }

}
