package com.project.always.security.oauth.controller;

import com.project.always.security.oauth.dto.request.UserSurveyRequestDto;
import com.project.always.security.oauth.dto.response.SurveyResponseDto;
import com.project.always.security.oauth.oauth2.UserPrincipal;
import com.project.always.security.oauth.service.UserMbtiService;
import com.project.always.security.oauth.service.UserService;
import com.project.always.security.oauth.service.UserSurveyService;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.service.SurveyService;
import com.project.always.utils.SuccessResponse;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserControllerApi {

    private final UserService userService;

    private final UserMbtiService userMbtiService;

    private final UserSurveyService userSurveyService;

    private final SurveyService surveyService;

    @GetMapping("/survey/{id}")
    public ResponseEntity<SuccessResponse> createUserSurvey(@PathVariable Long id) {

        Optional<Survey> surveyInfo = surveyService.getSurveyInfo(id);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("member.Survey.success")
                        .data(SurveyResponseDto.builder()
                                .questionText(surveyInfo.get().getQuestionText())
                                .option1(surveyInfo.get().getOption1())
                                .option2(surveyInfo.get().getOption2())
                                .build())
                        .build());
    }

    @PostMapping("/survey/select")
    public ResponseEntity<SuccessResponse> createUserSurvey(
            @RequestBody @Valid UserSurveyRequestDto userSurveyRequestDto) {

        userSurveyService.createUserSurveyOption(userSurveyRequestDto);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("member.createUserSurvey.success")
                        .data("data")
                        .build());
    }

    @PostMapping("/survey/mbti")
    public ResponseEntity<SuccessResponse> createUserMbti(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        userMbtiService.createUserMbti(userPrincipal.getUser().getId());

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("member.createUserSurvey.success")
                        .data("data")
                        .build());
    }
}
