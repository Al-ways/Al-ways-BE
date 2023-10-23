package com.project.always.security.oauth.service;

import static com.project.always.security.oauth.enums.AuthProvider.KAKAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.project.always.infrastructure.WithMockOAuth2User;
import com.project.always.mbti.domain.Mbti;
import com.project.always.mbti.repository.MbtiRepository;
import com.project.always.security.oauth.dto.request.UserMbtiRequestDto;
import com.project.always.security.oauth.dto.request.UserSurveyRequestDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserSurvey;
import com.project.always.security.oauth.enums.AuthProvider;
import com.project.always.security.oauth.enums.Role;
import com.project.always.security.oauth.repository.UserMbtiRepository;
import com.project.always.security.oauth.repository.UserRepository;
import com.project.always.security.oauth.repository.UserSurveyRepository;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.repository.SurveyRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMbtiServiceTest {

    @Autowired
    private MbtiRepository mbtiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserMbtiRepository userMbtiRepository;

    @Autowired
    private UserSurveyRepository userSurveyRepository;

    @DisplayName("유저가 7개 문항을 모두 응답하였다면 패턴을 생성할 수 있다. 일치한 패턴이 있다면 ")
    @Test
    void createUserMbti() throws Exception {
        //given
        User user = createUser(KAKAO, "testCobi", "password123", "testcobi@naver.com", Role.ROLE_USER, "qwerasdf12",
                "https://test_profile_image");
        userRepository.save(user);

        List<Survey> surveyList = surveyRepository.findAll();
        List<UserSurvey> userSurveys = surveyList.stream().map(survey -> UserSurvey.builder()
                .user(user)
                .survey(survey)
                .select_option(1)
                .build()).collect(Collectors.toList());

        userSurveyRepository.saveAll(userSurveys);
        List<UserSurvey> allByUser = userSurveyRepository.findAllByUser(user);

        Optional<Mbti> byPattern = mbtiRepository.findByPattern(allByUser.stream()
                .map(u -> u.getSelect_option())
                .collect(Collectors.toList())
                .toString());

        //when
        userMbtiService.createUserMbti(user.getId());

        //then
        assertThat(userMbtiRepository.findByUserAndMbti(user, byPattern.get()).isPresent()).isTrue();
    }

    @Autowired
    private UserMbtiService userMbtiService;

    public Survey createSurveyForm(final String questionText, final String option1, final String option2) {
        return Survey.builder()
                .questionText(questionText)
                .option1(option1)
                .option2(option2)
                .build();
    }

    public User createUser(final AuthProvider authProvider, final String name, final String password,
                           final String email, Role roleUser, String oauth2Id, String profileImage) {
        return User.builder()
                .authProvider(authProvider)
                .name(name)
                .password(password)
                .email(email)
                .role(roleUser)
                .oauth2Id(oauth2Id)
                .profileImage(profileImage)
                .build();
    }

    public UserSurvey createUserSurveyForm(final User user, final Survey survey, final int select_option) {
        return UserSurvey.builder()
                .user(user)
                .survey(survey)
                .select_option(select_option)
                .build();
    }

}