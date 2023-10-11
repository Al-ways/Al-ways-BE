package com.project.always.security.oauth.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.always.AcceptanceTest;
import com.project.always.controller.api.BaseControllerTest;
import com.project.always.infrastructure.WithMockOAuth2User;
import com.project.always.security.oauth.dto.UserNameRequestDto;
import com.project.always.security.oauth.dto.UserNameResponseDto;
import com.project.always.security.oauth.dto.UserResponseDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.jwt.JwtTokenProvider;
import com.project.always.security.oauth.repository.UserRepository;
import com.project.always.security.oauth.service.CustomOAuth2UserService;
import com.project.always.security.oauth.service.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.mockito.BDDMockito.given;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class OauthControllerApiTest extends AcceptanceTest {

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
    @DisplayName("사용자 이름 변경")
    @WithMockOAuth2User
    void modifyName() throws Exception {

        // given
        UserNameRequestDto dto = UserNameRequestDto.builder()
                .name("김철수").build();

        // when
        ResultActions result = mockMvc.perform(put("/api/oauth2/user/name")
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + "ACCESS_TOKEN")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(document(DEFAULT_RESTDOC_PATH,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("응답 데이터")
                        )
                ));
    }

}