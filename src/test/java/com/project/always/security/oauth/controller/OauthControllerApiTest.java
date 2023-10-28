package com.project.always.security.oauth.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.always.AcceptanceTest;
import com.project.always.infrastructure.WithMockOAuth2User;
import com.project.always.security.oauth.dto.request.UserNameRequestDto;
import com.project.always.security.oauth.dto.response.UserMyPageResponseDto;
import com.project.always.security.oauth.enums.AuthProvider;
import com.project.always.security.oauth.enums.Role;
import com.project.always.security.oauth.jwt.JwtTokenProvider;
import com.project.always.security.oauth.service.CustomOAuth2UserService;
import com.project.always.security.oauth.service.UserService;
import com.project.always.utils.S3Service;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;

import static org.mockito.BDDMockito.given;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

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

    @MockBean
    S3Service s3Service;

    @Test
    @DisplayName("사용자 이름 변경")
    @WithMockOAuth2User
    void modifyName() throws Exception {

        // given
        UserNameRequestDto dto = UserNameRequestDto.builder()
                .name("김철수").build();

        // when
        ResultActions result = mockMvc.perform(
                put("/api/oauth2/user/name")
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


    @Test
    @DisplayName("프로필 이미지 조회 테스트")
    @WithMockOAuth2User
    void getProfileImage() throws Exception {

        // given
        given(userService.getProfileImage(anyLong()))
                .willReturn(Optional.of("https://image.storage.com/profile/1"));

        // when
        ResultActions result = mockMvc.perform(get("/api/oauth2/profile")
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + "ACCESS_TOKEN"));

        // then
        result.andExpect(status().isOk())
                .andDo(document(DEFAULT_RESTDOC_PATH,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING).description("응답 프로필 url")
                        )
                ));
    }

    @Test
    @DisplayName("마이페이지 회원정보 조회 테스트")
    @WithMockOAuth2User
    void getUserInfoMypage() throws Exception {

        // given
        given(userService.getUserInfoMypage(anyLong()))
                .willReturn(UserMyPageResponseDto.builder()
                        .id(1L)
                        .authProvider(AuthProvider.KAKAO)
                        .name("testName1")
                        .email("test@test1.com")
                        .role(Role.ROLE_USER)
                        .profileImage("https://test_profile_image")
                        .build());

        // when
        ResultActions result = mockMvc.perform(get("/api/oauth2/mypage")
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + "ACCESS_TOKEN"));

        // then
        result.andExpect(status().isOk())
                .andDo(document(DEFAULT_RESTDOC_PATH,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("응답 데이터"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("응답 데이터"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("응답 데이터"),
                                fieldWithPath("data.authProvider").type(JsonFieldType.STRING).description("응답 데이터"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("응답 데이터"),
                                fieldWithPath("data.profileImage").type(JsonFieldType.STRING).description("응답 데이터")
                        )
                ));
    }

    @Test
    @DisplayName("프로필 이미지 수정 테스트")
    @WithMockOAuth2User
    void postProfileImageUpload() throws Exception {

        // given
        MockMultipartFile image = new MockMultipartFile(
                "image", "image.png", "image/png", "image data".getBytes());
        given(s3Service.upload(any(MultipartFile.class), eq("profile")))
                .willReturn("https://image.storage.com/profile/1");

        // when
        ResultActions result = mockMvc.perform(multipart("/api/oauth2/profile/modify")
                .file(image)
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + "ACCESS_TOKEN")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isCreated())
                .andDo(document(DEFAULT_RESTDOC_PATH,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        requestParts(
                                partWithName("image").description("프로필 이미지")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING).description("응답 프로필 url")
                        )
                ));
    }


}