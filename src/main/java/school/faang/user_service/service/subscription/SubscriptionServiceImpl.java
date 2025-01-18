package school.faang.user_service.service.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.user.UserDto;
import school.faang.user_service.dto.user.UserFilterDto;
import school.faang.user_service.entity.User;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.filter.user_filter.UserFilter;
import school.faang.user_service.mapper.UserMapper;
import school.faang.user_service.repository.SubscriptionRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;
    private final UserMapper userMapper;
    private final List<UserFilter> userFilters;

    @Override
    public void followUser(long followerId, long followeeId) {

        if (subscriptionRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {

            throw new DataValidationException("Subscription is already exist");
        }

        subscriptionRepository.followUser(followerId, followeeId);
    }

    @Override
    public void unfollowUser(long followerId, long followeeId) {

        if (!subscriptionRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {

            throw new DataValidationException("Subscription is doesn't exist");
        }

        subscriptionRepository.unfollowUser(followerId, followeeId);
    }

    @Override
    public Integer getFollowersCount(long followerId) {

        return subscriptionRepository.findFolloweesAmountByFollowerId(followerId);
    }

    @Override
    public List<UserDto> getFollowing(UserFilterDto filters, long followeeId) {

        Stream<User> users = subscriptionRepository.findByFolloweeId(followeeId);

        userFilters.stream()
                .filter(filter -> filter.isApplicable(filters))
                .forEach(filter -> filter.apply(users, filters));

        return users.map(userMapper::toDto).toList();
    }

    @Override
    public Integer getFollowingCount(long followerId) {

        return subscriptionRepository.findFolloweesAmountByFollowerId(followerId);
    }
}
