package com.project.always.security.oauth.dto.response;

import static javax.persistence.FetchType.LAZY;

import com.project.always.security.oauth.entity.User;
import com.project.always.survey.domain.Survey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private int select_option;

    @Builder
    private UserSurveyResponse(final User user, final Survey survey, final int select_option) {
        this.user = user;
        this.survey = survey;
        this.select_option = select_option;
    }

}
