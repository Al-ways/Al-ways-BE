package com.project.always.security.oauth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponseDto {

    private String questionText;

    private String option1;

    private String option2;

    @Builder
    private SurveyResponseDto(final String questionText, final String option1, final String option2) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
    }

}
