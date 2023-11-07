package com.project.always.review.api;

import com.project.always.controller.api.BaseControllerTest;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

class ReviewControllerApiTest extends BaseControllerTest {
    private static final Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("barId").type(JsonFieldType.NUMBER).description("바아이디"),
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저아이디"),
            fieldWithPath("select_rating").type(JsonFieldType.NUMBER).description("점수"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
    );
    private static final Snippet RESPONSE_FIELDS = responseFields(
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
            fieldWithPath("response").type(JsonFieldType.ARRAY).description("응답 데이터 목록"),
            fieldWithPath("response[].id").type(JsonFieldType.NUMBER).description("리뷰id"),
            fieldWithPath("response[].select_rating").type(JsonFieldType.NUMBER).description("평점"),
            fieldWithPath("response[].content").type(JsonFieldType.STRING).description("내용"),

            fieldWithPath("error").type(JsonFieldType.NULL).description("에러")

    );
    @Test
    void insert() throws Exception{
        net.minidev.json.JSONObject requestBody = new JSONObject();
        requestBody.put("barId", 2);
        requestBody.put("userId",1);
        requestBody.put("select_rating",5.0);
        requestBody.put("content","너무 맛있고 분위기 좋아요");


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,REQUEST_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                //.param("title",title)
                .body(requestBody)
                .log().all()

                .when()
                .post("/review/create")

                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void delete() {

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                //.param("title",title)
                .log().all()

                .when()
                .delete("/review/delete?reviewId=1")

                .then()
                .statusCode(HttpStatus.OK.value());
}

    @Test
    void update() {
        net.minidev.json.JSONObject requestBody = new JSONObject();
        requestBody.put("barId", 2);
        requestBody.put("userId",1);
        requestBody.put("select_rating",4.0);
        requestBody.put("content","너무 맛있고 분위기 좋아요");


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,REQUEST_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                //.param("title",title)
                .body(requestBody)
                .log().all()

                .when()
                .put("/review/update/1")

                .then()
                .statusCode(HttpStatus.OK.value());
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