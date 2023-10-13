package com.project.always.security.oauth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNameResponseDto {

    private String name;

    @Builder
    public UserNameResponseDto(String name) {
        this.name = name;
    }
}
