package com.project.always.security.oauth.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.always.AcceptanceTest;
import com.project.always.infrastructure.WithMockOAuth2User;
import com.project.always.security.oauth.dto.request.UserNameRequestDto;
import com.project.always.security.oauth.dto.request.UserSurveyRequestDto;
import com.project.always.security.oauth.jwt.JwtTokenProvider;
import com.project.always.security.oauth.service.CustomOAuth2UserService;
import com.project.always.security.oauth.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.ResultActions;

class UserControllerApiTest extends AcceptanceTest {
    private static final String BEARER_TYPE = "Bearer ";

    private static final Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름")
    );

    private static final Snippet RESPONSE_FIELDS = responseFields(
            fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
            fieldWithPath("data").type(JsonFieldType.STRING).description("응답 데이터")
    );

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    UserService userService;

    @MockBean
    CustomOAuth2UserService authService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("사용자는 설문지를 응답할 수 있다.")
    void createUserSurvey() throws Exception {

        // given
        UserSurveyRequestDto request = UserSurveyRequestDto.builder()
                .user_id(2L)
                .survey_id(1L)
                .select_option(1)
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/api/user/survey/select")
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + "ACCESS_TOKEN")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(document(DEFAULT_RESTDOC_PATH,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        requestFields(
                                fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("사용자 번호"),
                                fieldWithPath("survey_id").type(JsonFieldType.NUMBER).description("설문지 번호"),
                                fieldWithPath("select_option").type(JsonFieldType.NUMBER).description("선택 옵션")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("응답 데이터")
                        )
                ));
    }

    @Test
    @DisplayName("사용자는 6가지 설문을 응답하면, mbti 생성할 수 있다.")
    @WithMockOAuth2User
    void createUserMbti() throws Exception {

        // when
        ResultActions result = mockMvc.perform(post("/api/user/survey/mbti")
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + "ACCESS_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(document(DEFAULT_RESTDOC_PATH,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("응답 데이터")
                        )
                ));
    }
}