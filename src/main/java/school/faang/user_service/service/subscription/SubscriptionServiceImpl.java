package school.faang.user_service.service.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.user.UserDto;
import school.faang.user_service.dto.user.UserFilterDto;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.mapper.UserMapper;
import school.faang.user_service.repository.SubscriptionRepository;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{

    private SubscriptionRepository subscriptionRepository;
    private UserMapper userMapper;

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
    public int getFollowersCount(long followerId) {

        return subscriptionRepository.findFolloweesAmountByFollowerId(followerId);
    }

    @Override
    public List<UserDto> getFollowing(long followeeId) {

        return subscriptionRepository.findByFolloweeId(followeeId).map((x) -> userMapper.toDto(x)).toList();
    }

    private List<UserDto> filterUsers(UserFilterDto filters, List<UserDto> list) {

        if (filters == null)
            return list;

        return list.stream().filter(x -> x.equals(userMapper.toEntity(filters))).toList();
    }
}
