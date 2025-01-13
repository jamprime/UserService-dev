package school.faang.user_service.service.skill;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.dto.skill.SkillCandidateDto;
import school.faang.user_service.dto.skill.SkillDto;
import school.faang.user_service.entity.Skill;
import school.faang.user_service.entity.User;
import school.faang.user_service.entity.UserSkillGuarantee;
import school.faang.user_service.entity.recommendation.SkillOffer;
import school.faang.user_service.mapper.SkillMapper;
import school.faang.user_service.repository.SkillRepository;
import school.faang.user_service.repository.UserRepository;
import school.faang.user_service.repository.UserSkillGuaranteeRepository;
import school.faang.user_service.repository.recommendation.SkillOfferRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{

    private SkillRepository skillRepository;
    private SkillMapper skillMapper;
    private SkillOfferRepository skillOfferRepository;
    private UserSkillGuaranteeRepository skillGuaranteeRepository;
    private UserRepository userRepository;

    @Override
    public SkillDto create(SkillDto skill) {

        if (skillRepository.existsByTitle(skill.getTitle())) {
            throw new RuntimeException("There is exist skill with same title");
        }

        return skillMapper.toDto(skillRepository.save(skillMapper.toEntity(skill)));
    }

    @Override
    public List<SkillDto> getUserSkills(Long id) {

        return skillRepository.findAllByUserId(id).stream().map((x) -> skillMapper.toDto(x)).toList();
    }

    @Override
    public List<SkillCandidateDto> getOfferedSkills(long userId) {

        return skillRepository.findSkillsOfferedToUser(userId).stream().map((x) -> skillMapper.toCandidateDto(x)).toList();
    }

    @Override
    public SkillDto acquireSkillFromOffers(long skillId, long userId) {

        Optional<Skill> skill = skillRepository.findUserSkill(skillId,userId);

        if (skill.isPresent()) {

            return skillMapper.toDto(skill.get());
        } else {

            List<SkillOffer> allOffersOfSkill = skillOfferRepository.findAllOffersOfSkill(skillId, userId);

            if (allOffersOfSkill.size() >= 3) {

                skillRepository.assignSkillToUser(skillId, userId);

                for (SkillOffer skillOffer : allOffersOfSkill) {

                    skillGuaranteeRepository.save(new UserSkillGuarantee(null,
                            skillOffer.getRecommendation().getReceiver(),
                            skillOffer.getSkill(),
                            skillOffer.getRecommendation().getAuthor()));
                }
            }
            return skillMapper.toDto(skillRepository.findUserSkill(skillId, userId).get());
        }
    }

    private User getUserById(long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " doesn't exist"));
    }
}