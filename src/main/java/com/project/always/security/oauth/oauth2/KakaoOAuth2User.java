package com.project.always.security.oauth.oauth2;

import java.util.Map;

public class KakaoOAuth2User extends OAuth2UserInfo {

    private Integer id;

    public KakaoOAuth2User(Map<String, Object> attributes) {
        super(attributes);
        this.id = (Integer) attributes.get("id");
    }


    @Override
    public String getOAuth2Id() {
        return this.id.toString();
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("nickname");
    }
}