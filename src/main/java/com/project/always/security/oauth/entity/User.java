package com.project.always.security.oauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.always.bar.domain.UserBar;
import com.project.always.security.oauth.enums.Role;
import com.project.always.security.oauth.oauth2.OAuth2UserInfo;

import lombok.*;

import javax.persistence.*;

import com.project.always.security.oauth.enums.AuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.project.always.security.oauth.enums.Role.ROLE_GUEST;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 512)
    private String profileImage;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserBar> userBars = new ArrayList<>();


    public User update(OAuth2UserInfo oAuth2UserInfo) {
        this.name = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();

        return this;
    }
    @Builder
    private User(final Long id, final String email, final String password, final String name,
                 final AuthProvider authProvider, final String oauth2Id, final Role role, final String profileImage) {
        this.id = id;
        this.email = email;
        this.password = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.authProvider = authProvider;
        this.profileImage = profileImage; // TODO profileImage ? default(test_profile_image) : oAuthUserInfo.getProfileImage()
        this.oauth2Id = oauth2Id;
        this.role = ROLE_GUEST; // to do
    }
  
    public void setName(String name) {this.name = name;}

}