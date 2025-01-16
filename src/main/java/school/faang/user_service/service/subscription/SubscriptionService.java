package school.faang.user_service.service.subscription;

import school.faang.user_service.dto.user.UserDto;

import java.util.List;

public interface SubscriptionService {

    void followUser(long followerId, long followeeId);

    void unfollowUser(long followerId, long followeeId);

    int getFollowersCount(long followerId);

    List<UserDto> getFollowing(long followeeId);

}
