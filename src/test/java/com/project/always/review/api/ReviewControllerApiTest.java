package com.project.always.review.api;

import com.project.always.controller.api.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

class ReviewControllerApiTest extends BaseControllerTest {
    private static final Snippet RESPONSE_FIELDS = responseFields(
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
            fieldWithPath("response").type(JsonFieldType.ARRAY).description("응답 데이터 목록"),
            fieldWithPath("response[].id").type(JsonFieldType.NUMBER).description("리뷰id"),
            fieldWithPath("response[].select_rating").type(JsonFieldType.NUMBER).description("평점"),
            fieldWithPath("response[].content").type(JsonFieldType.STRING).description("내용"),

            fieldWithPath("error").type(JsonFieldType.NULL).description("에러")

    );
    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getReviewsByBarId() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/review/bar?id=1")

                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getReviewsByUserId() {
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/review/user?id=1")

                .then()
                .statusCode(HttpStatus.OK.value());
    }
}