package com.project.always.security.oauth.dto.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMbtiRequestDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long mbti_id;


    @Builder
    private UserMbtiRequestDto(final Long user_id, final Long mbti_id) {
        this.user_id = user_id;
        this.mbti_id = mbti_id;
    }

}