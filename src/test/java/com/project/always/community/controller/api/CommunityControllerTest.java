package com.project.always.community.controller.api;

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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

class CommunityControllerTest extends BaseControllerTest {
    private static final Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("category_id").type(JsonFieldType.NUMBER).description("카테고리아이디"),
            fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("유저아이디"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
    );
    @Test
    void insert()  throws Exception{
        net.minidev.json.JSONObject requestBody = new JSONObject();
        requestBody.put("category_id", 1);
        requestBody.put("user_id",1);
        requestBody.put("title","오늘은 맛집에 갔다");
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
                .post("/community/create")

                .then()
                .statusCode(HttpStatus.OK.value());
    }
}