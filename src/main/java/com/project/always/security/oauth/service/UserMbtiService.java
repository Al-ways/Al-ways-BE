package com.project.always.security.oauth.service;

import com.project.always.mbti.domain.Mbti;
import com.project.always.mbti.repository.MbtiRepository;
import com.project.always.security.oauth.dto.request.UserMbtiRequestDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserMbti;
import com.project.always.security.oauth.entity.UserSurvey;
import com.project.always.security.oauth.oauth2.UserPrincipal;
import com.project.always.security.oauth.repository.UserMbtiRepository;
import com.project.always.security.oauth.repository.UserRepository;
import com.project.always.security.oauth.repository.UserSurveyRepository;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.repository.SurveyRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserMbtiService {

    private final UserRepository userRepository;
    private final MbtiRepository mbtiRepository;
    private final UserMbtiRepository userMbtiRepository;
    private final UserSurveyRepository userSurveyRepository;

    @Transactional
    public void createUserMbti(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        List<UserSurvey> allByUser = userSurveyRepository.findAllByUser(user);

        if (allByUser.size() != 6){
            throw new IllegalArgumentException("6개 문항을 모두 응답해주세요.");
        }
        Optional<Mbti> byPattern = getByPattern(allByUser);

        if (!byPattern.isPresent()) {
            throw new IllegalArgumentException("일치하지 않는 패턴입니다.");
        }

        UserMbti usermbti = UserMbti.builder()
                .mbti(byPattern.get())
                .user(user)
                .build();

        userMbtiRepository.save(usermbti);
    }

    private Optional<Mbti> getByPattern(List<UserSurvey> allByUser) {
        return mbtiRepository.findByPattern(allByUser.stream()
                .map(u -> u.getSelect_option())
                .collect(Collectors.toList())
                .toString());
    }

}
