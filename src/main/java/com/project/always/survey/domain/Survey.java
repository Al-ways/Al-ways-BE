package com.project.always.survey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    private String questionText;

    private String option1;

    private String option2;

    @Builder
    private Survey(final String questionText, final String option1, final String option2) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
    }
}