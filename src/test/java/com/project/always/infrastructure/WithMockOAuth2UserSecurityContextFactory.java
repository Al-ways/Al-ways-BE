package com.project.always.infrastructure;

import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.enums.AuthProvider;
import com.project.always.security.oauth.enums.Role;
import com.project.always.security.oauth.oauth2.UserPrincipal;
import java.util.Collections;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockOAuth2UserSecurityContextFactory implements
        WithSecurityContextFactory<WithMockOAuth2User> {

    @Override
    public SecurityContext createSecurityContext(WithMockOAuth2User annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UserPrincipal principal =
                new UserPrincipal(User.builder()
                        .id(1L)
                        .authProvider(AuthProvider.KAKAO)
                        .name("testName1")
                        .email("test@test1.com")
                        .role(Role.ROLE_USER)
                        .oauth2Id("qwerasdf12")
                        .profileImage("https://test_profile_image")
                        .build());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());

        context.setAuthentication(auth);

        return context;
    }
}