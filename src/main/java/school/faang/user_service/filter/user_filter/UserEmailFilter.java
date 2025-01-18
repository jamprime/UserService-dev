package school.faang.user_service.filter.user_filter;

import org.springframework.stereotype.Component;
import school.faang.user_service.dto.user.UserFilterDto;
import school.faang.user_service.entity.User;

import java.util.stream.Stream;

@Component
public class UserEmailFilter implements UserFilter {

    @Override
    public boolean isApplicable(UserFilterDto filter) {
        return filter.getEmailPattern() != null;
    }

    @Override
    public void apply(Stream<User> stream, UserFilterDto filters) {
        stream.filter(user -> user.getEmail().equals(filters.getEmailPattern()));
    }
}
