package com.project.always.security.oauth.dto.request;

import com.project.always.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurveyRequestDto {
    private String questionText;

    private String option1;

    private String option2;

    @Builder
    private SurveyRequestDto(String questionText, String option1, String option2) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
    }

}
