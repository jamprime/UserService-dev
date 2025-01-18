package school.faang.user_service.filter;

import java.util.stream.Stream;

public interface Filter<T, F> {

    boolean isApplicable(F filters);

    void apply(Stream<T> stream, F filters);

}
