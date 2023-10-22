package com.project.always.security.oauth.jwt;

import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //1. Request Header 에서 JWT Token 추출
        String token = jwtTokenProvider.resolveToken(request);


        //2. validateToken 메서드로 토큰 유효성 검사
        if (token != null && Boolean.TRUE.equals(jwtTokenProvider.validateToken(token))) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);;
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("save: " + authentication.getName() + "credentials");
        } else {
            log.debug("no valid JWT tokens.");
        }
        filterChain.doFilter(request, response);
    }

}
