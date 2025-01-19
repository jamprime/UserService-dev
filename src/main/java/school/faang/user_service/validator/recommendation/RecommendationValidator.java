package school.faang.user_service.validator.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.faang.user_service.dto.recommendation.RecommendationDto;
import school.faang.user_service.exception.DataValidationException;

@Component
@RequiredArgsConstructor
public class RecommendationValidator {


    public void validateRecommendation(RecommendationDto recommendation) {

        if (recommendation.getContent().isEmpty()) {

            throw new DataValidationException("Recommendation can't be empty");
        }

    }
}
