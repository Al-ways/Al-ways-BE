package com.project.always.userbar.controller.api;

import com.project.always.bar.mapper.BarMapper;
import com.project.always.bar.service.BarService;
import com.project.always.controller.api.BaseControllerTest;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import javax.annotation.Resource;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

class UserBarControllerTest extends BaseControllerTest {
    @Resource
    BarMapper barMapper;

    @Autowired
    BarService barService;

    private static final Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("userbarId").type(JsonFieldType.NUMBER).description("유저바아이디"),
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저아이디"),
            fieldWithPath("barId").type(JsonFieldType.NUMBER).description("바아이디")
    );

    @DisplayName("user bar like post test")
    @Test
    void userbarLIKE_test() throws Exception{

        net.minidev.json.JSONObject requestBody = new JSONObject();
        requestBody.put("userbarId", 2);
        requestBody.put("userId",1);
        requestBody.put("barId",2);


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,REQUEST_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                //.param("title",title)
                .body(requestBody)
                .log().all()

                .when()
                .post("/userbar/like")

                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @DisplayName("user bar unlike post test")
    @Test
    void userbarUNLIKE_test() throws Exception{

        net.minidev.json.JSONObject requestBody = new JSONObject();
        requestBody.put("userbarId", 1);
        requestBody.put("userId",1);
        requestBody.put("barId",1);


        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,REQUEST_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                //.param("title",title)
                .body(requestBody)
                .log().all()

                .when()
                .delete("/userbar/unlike")

                .then()
                .statusCode(HttpStatus.OK.value());
    }


}