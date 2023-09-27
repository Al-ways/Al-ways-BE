<<<<<<<< HEAD:src/main/java/com/project/always/survey/domain/Survey.java
package com.project.always.survey.domain;
========
package com.project.always.survey;
>>>>>>>> dev:src/main/java/com/project/always/survey/Survey.java

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
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
}