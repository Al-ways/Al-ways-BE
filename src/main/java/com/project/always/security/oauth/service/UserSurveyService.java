package com.project.always.security.oauth.service;

import com.project.always.security.oauth.dto.request.UserSurveyRequestDto;
import com.project.always.security.oauth.dto.request.UserSurveyRequestDto.UserSurveyRequestDtoBuilder;
import com.project.always.security.oauth.dto.response.UserResponseDto;
import com.project.always.security.oauth.dto.response.UserSurveyResponse;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserSurvey;
import com.project.always.security.oauth.repository.UserRepository;
import com.project.always.security.oauth.repository.UserSurveyRepository;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.repository.SurveyRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSurveyService {

    private final UserSurveyRepository userSurveyRepository;
    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    @Transactional
    public void createUserSurveyOption(UserSurveyRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Survey survey = surveyRepository.findById(requestDto.getSurvey_id())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 설문지 문항입니다."));

        if (userSurveyRepository.findByUserAndSurvey(user, survey).isPresent()){

            throw new RuntimeException("회원이 응답한 설문지입니다.");
        }

        UserSurvey userSurvey = UserSurvey.builder()
                .survey(survey)
                .user(user)
                .select_option(requestDto.getSelect_option())
                .build();

        userSurveyRepository.save(userSurvey);
    }
}
