package com.project.always.community.controller;


import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.community.domain.Community;
import com.project.always.community.dto.CommunityDTO;
import com.project.always.community.dto.CommunityRequestDTO;
import com.project.always.community.mapper.CommunityMapper;
import com.project.always.community.mapper.CommunityRequestMapper;
import com.project.always.community.service.CommunityService;
import com.project.always.security.oauth.entity.User;
import com.project.always.utils.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
@Controller
public class CommunityController {
    private final CommunityService communityService;
    private final CommunityRequestMapper communityRequestMapper;
    private final CommunityMapper communityMapper;

    @PostMapping("/create")
    public HttpResponseEntity.ResponseResult<String> insert(@RequestBody CommunityRequestDTO communityRequestDTO){
        communityService.insert(communityRequestDTO);
        return success();
    }

    @Transactional
    @DeleteMapping("/delete")
    public HttpResponseEntity.ResponseResult<String> delete(@RequestParam Long communityId){
        communityService.delete(communityId);
        return success();
    }

    @PutMapping("/update")
    public HttpResponseEntity.ResponseResult<String> update(@RequestParam Long communityId, @RequestBody CommunityRequestDTO communityRequestDTO){
        communityService.update(communityRequestDTO,communityId);
        return success();
    }

    @GetMapping("/modified/desc") //최신순
    public HttpResponseEntity.ResponseResult<List<CommunityDTO>> getCommunityOrderByModifiedAtDesc(){
        return success(communityMapper.toDtoList(communityService.getCommunityOrderByModifiedAtDesc()));
    }
    @GetMapping("/modified/asc") //과거순
    public HttpResponseEntity.ResponseResult<List<CommunityDTO>> getCommunityOrderByModifiedAtAsc(){
        return success(communityMapper.toDtoList(communityService.getCommunityOrderByModifiedAtAsc()));
    }
    @GetMapping("/hit/asc") //조회수 높은수
    public HttpResponseEntity.ResponseResult<List<CommunityDTO>> getCommunityOrderByHit(){
        return success(communityMapper.toDtoList(communityService.getCommunityOrderByHit()));
    }
    @GetMapping("/byid") //id로 조회
    public HttpResponseEntity.ResponseResult<CommunityDTO> getCommunityById(@RequestParam Long communityId){
        return success(communityMapper.toDto(communityService.getCommunityById(communityId)));
    }
    //category에 따른조회
}
