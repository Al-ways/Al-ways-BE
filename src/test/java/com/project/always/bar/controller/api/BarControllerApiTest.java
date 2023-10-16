package com.project.always.bar.controller.api;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.dto.BarDTO;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static com.project.always.utils.HttpResponseEntity.success;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import com.project.always.bar.service.BarService;
import com.project.always.controller.api.BaseControllerTest;
import com.project.always.utils.HttpResponseEntity;
import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;
import com.project.always.bar.mapper.BarMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class BarControllerApiTest extends BaseControllerTest {

    @Resource
    BarMapper barMapper;

    @Autowired
    BarService barService;
    private static final Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("title").type(JsonFieldType.STRING).description("술집이름")
    );
    private static final Snippet RESPONSE_FIELDS = responseFields(
            fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
            fieldWithPath("response").type(JsonFieldType.ARRAY).description("응답 데이터 목록"),
            fieldWithPath("response[].id").type(JsonFieldType.NUMBER).description("술집id"),
            fieldWithPath("response[].title").type(JsonFieldType.STRING).description("이름"),
            fieldWithPath("response[].location").type(JsonFieldType.STRING).description("위치"),
            fieldWithPath("response[].rating").type(JsonFieldType.NUMBER).description("평점"),
            fieldWithPath("response[].image").type(JsonFieldType.STRING).description("사진"),
            fieldWithPath("response[].tel").type(JsonFieldType.STRING).description("전화번호"),
            fieldWithPath("response[].lat").type(JsonFieldType.STRING).description("위도"),
            fieldWithPath("response[].log").type(JsonFieldType.STRING).description("경도"),
            fieldWithPath("response[].open_status").type(JsonFieldType.STRING).description("영업중"),
            fieldWithPath("response[].group_seat").type(JsonFieldType.STRING).description("좌석수"),
            fieldWithPath("response[].hit").type(JsonFieldType.NUMBER).description("조회수"),
            fieldWithPath("error").type(JsonFieldType.NULL).description("에러")

            );

    @Transactional
    @DisplayName("get all bar list")
    @Test
    void barAllList_test() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/bar/allbar")

                .then()
                .statusCode(HttpStatus.OK.value());

    }
    @DisplayName("get bar list by title")
    @Test
    void barTitleList_test() throws Exception{
        String title = "g";
/*
        net.minidev.json.JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        List<BarDTO> response = barMapper.toDtoList(barService.findByTitleContaining(title));
*/

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                //.param("title",title)
                .log().all()

                .when()
                .get("/bar/bytitle?title=꼼주")


                .then()
                .statusCode(HttpStatus.OK.value()); // 술집 번호 2인지 확인;
    }


    @DisplayName("get bar list by Location")
    @Test
    void barLocationList_test() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/bar/bylocation?location=서울")

                .then()
                .statusCode(HttpStatus.OK.value()); // 술집 번호 2인지 확인;
    }
    @DisplayName("get bar list by tag")
    @Test
    void barTagList_test() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/bar/bytag?tagName=시끌벅적한")

                .then()
                .statusCode(HttpStatus.OK.value()); // 술집 번호 2인지 확인;
    }
    @DisplayName("get bar list by category")
    @Test
    void barCategoryList_test() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/bar/bycate?category=일본식주점")

                .then()
                .statusCode(HttpStatus.OK.value()); // 술집 번호 2인지 확인;
    }
    @DisplayName("get top 3 bar list by location")
    @Test
    void barTop3ByLocationList_test() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/bar/popularity?location=노원구")

                .then()
                .statusCode(HttpStatus.OK.value()); // 술집 번호 2인지 확인;
    }

    @DisplayName("get top 3 bar list by location")
    @Test
    void barRatingByLocationList_test() throws Exception{
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,RESPONSE_FIELDS)) // API 문서 관련 필터 추가
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                //.body(response)
                .log().all()

                .when()
                .get("/bar/byrating?location=노원구")

                .then()
                .statusCode(HttpStatus.OK.value()); // 술집 번호 2인지 확인;
    }
}