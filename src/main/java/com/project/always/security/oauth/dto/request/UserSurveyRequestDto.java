package com.project.always.security.oauth.dto.request;

import static javax.persistence.FetchType.LAZY;

import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserSurvey;
import com.project.always.survey.domain.Survey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSurveyRequestDto {

    private Long user_id;

    private Long survey_id;

    private int select_option;

    @Builder
    private UserSurveyRequestDto(final Long user_id, final Long survey_id,final int select_option) {
        this.user_id = user_id;
        this.survey_id = survey_id;
        this.select_option = select_option;
    }

}
