package com.project.always.security.oauth.oauth2;

import com.project.always.security.oauth.dto.response.UserResponseDto;
import com.project.always.security.oauth.lib.CookieUtils;
import com.project.always.security.oauth.jwt.JwtTokenProvider;
import com.project.always.security.oauth.repository.CookieAuthorizationRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.project.always.security.oauth.repository.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${oauth.authorizedRedirectUri}")
    private String oauthRedirectUri;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Value("${app.origin.url}")
    private String originUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        log.info("cookieAuthorizationRequestRepository = {}", cookieAuthorizationRequestRepository);
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed.");
            return;
        }
        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {


        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new RuntimeException("redirect URIs are not matched.");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        //JWT 생성
        UserResponseDto tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 토큰정보(리프레시토큰, 엑세스토큰) 를 cookie에 저장
        response.addHeader("Set-Cookie", tokenInfo.getRefreshToken().toString());
        response.addHeader("Set-Cookie", tokenInfo.getAccessToken().toString());

        // 헤더정보 추가
        response.addHeader("Authorization", "Bearer " + tokenInfo.getAccessToken());

        return UriComponentsBuilder.fromUriString(originUrl +"/oauth2/redirect")
                .queryParam("token", tokenInfo.getAccessToken())
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create(oauthRedirectUri);

        if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort()) {
            return true;
        }
        return false;
    }

}
