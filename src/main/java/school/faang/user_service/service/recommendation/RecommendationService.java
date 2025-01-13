package school.faang.user_service.service.recommendation;


import school.faang.user_service.dto.recommendation.RecommendationDto;
import school.faang.user_service.entity.recommendation.Recommendation;

public interface RecommendationService {

    public Recommendation create(RecommendationDto recommendationDto);
}
