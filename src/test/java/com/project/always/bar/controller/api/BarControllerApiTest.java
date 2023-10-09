package com.project.always.bar.controller.api;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.project.always.bar.domain.Bar;
import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.mapper.BarMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("local")
public class BarControllerApiTest {

    @Resource
    BarMapper barMapper;

    @Resource
    MockMvc mockMvc;

/*    @Transactional
    @DisplayName("술집 전체 목록 조회")
    @Test
    void barAllList_test() throws Exception{

        String expectedTitle = "고부시";
        JSONObject requestBody = new JSONObject();
        requestBody.put("title",expectedTitle);

    }*/

}
