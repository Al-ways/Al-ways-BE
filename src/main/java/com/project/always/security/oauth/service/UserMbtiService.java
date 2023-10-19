package com.project.always.security.oauth.service;

import com.project.always.mbti.domain.Mbti;
import com.project.always.mbti.repository.MbtiRepository;
import com.project.always.security.oauth.dto.request.UserMbtiRequestDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserMbti;
import com.project.always.security.oauth.entity.UserSurvey;
import com.project.always.security.oauth.repository.UserMbtiRepository;
import com.project.always.security.oauth.repository.UserRepository;
import com.project.always.security.oauth.repository.UserSurveyRepository;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.repository.SurveyRepository;
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

    @Transactional
    public void createUserMbti(UserMbtiRequestDto requestDto){

        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Mbti mbti = mbtiRepository.findById(requestDto.getMbti_id())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 mbti입니다."));

        if (userMbtiRepository.findByUserAndMbti(user, mbti).isPresent()){

            throw new RuntimeException("mbti 결과가 존재합니다.");
        }

        UserMbti usermbti = UserMbti.builder()
                .mbti(mbti)
                .user(user)
                .build();

        userMbtiRepository.save(usermbti);
    }
}
