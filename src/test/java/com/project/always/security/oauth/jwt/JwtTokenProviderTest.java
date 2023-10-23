package com.project.always.security.oauth.jwt;

import static com.project.always.security.oauth.enums.Role.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.always.security.oauth.dto.response.UserResponseDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.enums.AuthProvider;
import com.project.always.security.oauth.enums.Role;
import com.project.always.security.oauth.oauth2.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String SECRET =
            "testSecretKeytestSecretKeytestSecretKeytestSecretKeytestSecretKeytestSecretKeytestSecretKey";

    private final UserPrincipal SOCiAL_LOGIN_USER =
            new UserPrincipal(User.builder()
                    .id(1L)
                    .authProvider(AuthProvider.KAKAO)
                    .name("testName1")
                    .email("test@test1.com")
                    .role(Role.ROLE_USER)
                    .oauth2Id("qwerasdf12")
                    .profileImage("https://test_profile_image")
                    .build());

    private SecretKey secretKey;

    private final long ONE_HOUR = 1L;

    @BeforeEach
    void init() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        this.jwtTokenProvider = new JwtTokenProvider(SECRET, userDetailsService);
    }


    @DisplayName("토큰 발급 테스트")
    @Test
    void createToken() throws Exception{
        //given
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singletonList(
                new SimpleGrantedAuthority(ROLE_USER.name()));
        UserResponseDto userResponseDto = jwtTokenProvider.generateToken("cobi", simpleGrantedAuthorities);

        //when //then
        assertThat(userResponseDto.getAccessToken()).isInstanceOf(String.class);
        assertThat(userResponseDto.getRefreshToken()).isInstanceOf(String.class);
    }


    @Test
    @DisplayName("token 검사 성공")
    void validateToken() {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 1000L * 60 * 60);

        String token = Jwts.builder()
                .signWith(secretKey)
                .setSubject("1")
                .claim("role", "ROLE_USER")
                .setIssuer("cobi1")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();

        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    @DisplayName("만료된 토큰일 경우 false를 리턴한다.")
    void validateByExpiredToken() {
        Date now = new Date();
        Date validity = new Date(now.getTime());

        String token = Jwts.builder()
                .signWith(secretKey)
                .setSubject("1")
                .claim("role", "ROLE_USER")
                .setIssuer("cobi1")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();

        assertFalse(jwtTokenProvider.validateToken(token));
    }

    @Test
    @DisplayName("JWT 토큰 양식과 일치하지 않는 경우 false를 리턴한다.")
    void validateByUnsupportedToken() {
        assertFalse(jwtTokenProvider
                .validateToken(
                        "eyJhbGciOiJIUzI1NiJ9.NTY3ODkwRG9lIE2MjM5MDIyfQ."));
    }
}