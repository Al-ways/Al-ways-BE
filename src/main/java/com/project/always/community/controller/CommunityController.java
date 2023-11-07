package com.project.always.community.controller;


import com.project.always.community.domain.Community;
import com.project.always.community.dto.CommunityRequestDTO;
import com.project.always.community.mapper.CommunityRequestMapper;
import com.project.always.community.service.CommunityService;
import com.project.always.utils.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
@Controller
public class CommunityController {
    private final CommunityService communityService;
    private final CommunityRequestMapper communityRequestMapper;
    @PostMapping("/create")
    public HttpResponseEntity.ResponseResult<String> insert(@RequestBody CommunityRequestDTO communityRequestDTO){
        communityService.insert(communityRequestDTO);
        return success();
    }

}
