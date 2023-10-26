package com.project.always.security.oauth.service;

import static com.project.always.security.oauth.enums.AuthProvider.KAKAO;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.always.security.oauth.dto.request.UserSurveyRequestDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.enums.AuthProvider;
import com.project.always.security.oauth.enums.Role;
import com.project.always.security.oauth.repository.UserRepository;
import com.project.always.security.oauth.repository.UserSurveyRepository;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.repository.SurveyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserSurveyServiceTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSurveyRepository userSurveyRepository;

    @Autowired
    private UserSurveyService userSurveyService;


    @DisplayName("회원은 질문에 응답할 수 있고, 응답한 옵션은 db에 저장된다.")
    @Test
    void createUserSurveyOption() throws Exception {
        //given
        User user = createUser(KAKAO, "testCobi","password123", "testcobi@naver.com", Role.ROLE_USER, "qwerasdf12",
                "https://test_profile_image");
        Survey survey = createSurvey("질문1", "예", "아니요");
        int select_option = 1;
        userRepository.save(user);
        surveyRepository.save(survey);

        UserSurveyRequestDto request = UserSurveyRequestDto.builder()
                .user_id(user.getId())
                .survey_id(survey.getId())
                .select_option(select_option)
                .build();

        //when
        userSurveyService.createUserSurveyOption(request);

        //then
        assertThat(userSurveyRepository.findByUserAndSurvey(user, survey).isPresent()).isTrue();

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

    public Survey createSurvey(final String questionText, final String option1, final String option2) {
        return Survey.builder()
                .questionText(questionText)
                .option1(option1)
                .option2(option2)
                .build();
    }

}