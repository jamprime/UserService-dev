package school.faang.user_service.controller.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import school.faang.user_service.dto.user.UserDto;
import school.faang.user_service.dto.user.UserFilterDto;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.service.subscription.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    public void followUser(long followerId, long followeeId) {

        if (followerId == followeeId) {

            throw new DataValidationException("You can't follow yourself");
        }

        subscriptionService.followUser(followerId, followeeId);
    }

    private void unfollowUser(long followerId, long followeeId) {

        if (followerId == followeeId) {

            throw new DataValidationException("You can't unfollow yourself");
        }

        subscriptionService.unfollowUser(followerId, followeeId);
    }

    public int getFollowersCount(long followerId) {

        return subscriptionService.getFollowersCount(followerId);
    }

    public List<UserDto> getFollowing(long followeeId) {

        return subscriptionService.getFollowing(followeeId);
    }

}
