package school.faang.user_service.service.recommendation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.recommendation.RecommendationDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.entity.recommendation.Recommendation;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.repository.SkillRepository;
import school.faang.user_service.repository.UserSkillGuaranteeRepository;
import school.faang.user_service.repository.recommendation.RecommendationRepository;
import school.faang.user_service.repository.recommendation.SkillOfferRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{

    private final RecommendationRepository recommendationRepository;
    private final SkillOfferRepository skillOfferRepository;
    private final SkillRepository skillRepository;

    public Recommendation create(RecommendationDto recommendationDto) {

        Long id = recommendationRepository.create(recommendationDto.getAuthorId(),
                recommendationDto.getReceiverId(),
                recommendationDto.getContent());


        Optional<Recommendation> recommendation = recommendationRepository.findFirstByAuthorIdAndReceiverIdOrderByCreatedAtDesc(
                recommendationDto.getAuthorId(),
                recommendationDto.getReceiverId());

        if (recommendation.isPresent()) {
            if (ChronoUnit.MONTHS.between(recommendationDto.getCreatedAt(), recommendation.get().getCreatedAt()) <= 6) {

                throw new DataValidationException("It's been less than 6 months since your last recommendation.");
            }
        }

        Optional<Recommendation> out = recommendationRepository.findById(id);

        return (out.orElse(null));
    }


}
