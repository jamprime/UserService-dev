package school.faang.user_service.controller.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import school.faang.user_service.dto.recommendation.RecommendationDto;
import school.faang.user_service.service.recommendation.RecommendationService;
import school.faang.user_service.validator.recommendation.RecommendationValidator;

@RestController("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private RecommendationService recommendationService;
    private RecommendationValidator recommendationValidator;

    public void giveRecommendation(RecommendationDto recommendation) {

        recommendationValidator.validateRecommendation(recommendation);

        recommendationService.create(recommendation);
    }
}
