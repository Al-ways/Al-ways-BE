package com.project.always.security.oauth.dto.response;

import com.project.always.bar.domain.UserBar;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.enums.AuthProvider;
import com.project.always.security.oauth.enums.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMyPageResponseDto {

    private Long id;

    private String email;

    private String name;

    private AuthProvider authProvider;

    private Role role;

    private String profileImage;

    @Builder
    private UserMyPageResponseDto(Long id, String email, String name, AuthProvider authProvider, Role role,
                                 String profileImage) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.authProvider = authProvider;
        this.role = role;
        this.profileImage = profileImage;
    }

    public static UserMyPageResponseDto of(User user){
        return UserMyPageResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .authProvider(user.getAuthProvider())
                .role(user.getRole())
                .profileImage(user.getProfileImage())
                .build();
    }
}
