package com.project.always.security.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String grantType;
    private String accessToken;
    private Long accessTokenExpirationTime;
    private String refreshToken;
    private Long refreshTokenExpirationTime;

    @Builder
    private UserResponseDto(final String grantType, final String accessToken, final Long accessTokenExpirationTime,
                            final String refreshToken, final Long refreshTokenExpirationTime) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
