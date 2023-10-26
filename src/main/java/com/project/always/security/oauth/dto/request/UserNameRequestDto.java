package com.project.always.security.oauth.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNameRequestDto {

    private String name;

    @Builder
    private UserNameRequestDto(String name) {
        this.name = name;
    }
}
