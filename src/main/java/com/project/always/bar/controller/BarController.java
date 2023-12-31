package com.project.always.bar.controller;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.Review;
import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.dto.ReviewDTO;
import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.bar.mapper.BarMapper;
import com.project.always.bar.mapper.ReviewMapper;
import com.project.always.bar.repository.BarRepository;
import com.project.always.bar.service.BarService;
import com.project.always.bar.service.ReviewService;
import com.project.always.utils.GenericMapper;
import com.project.always.utils.HttpResponseEntity;
import com.project.always.utils.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bar")
public class BarController {

    private final BarService barService;
    private final BarMapper barMapper;
    private final BarRepository barRepository;


    /*@GetMapping("/allbar")
    public ResponseEntity<List<BarDTO>> getList(){
        List<BarDTO> barDTOList = barMapper.toDtoList(barService.findAll());
        return ResponseEntity.ok(barDTOList);
    }*/
    /*@GetMapping("/bytitle")
    public ResponseEntity<SuccessResponse> findByTitleContaining(@RequestParam @NotBlank String title){

        return ResponseEntity.ok(SuccessResponse.builder()
                .message("get.user.profileImage.success")
                .data(barService.findByTitleContaining(title))
                .build());
    }*/
    @GetMapping("/allbar")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> getList(){
        return success(barMapper.toDtoList(barService.findAll()));
    }

    @GetMapping("/{id}")
    public HttpResponseEntity.ResponseResult<BarDTO> getBar(@PathVariable Long id){
        return success(barMapper.toDto(barService.findById(id)));
    }

    @GetMapping("/bytitle")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> findByTitleContaining(@RequestParam @NotBlank String title){
        return success(barMapper.toDtoList(barService.findByTitleContaining(title)));
    }

    @GetMapping("/bylocation")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> findByLocationContaining(@RequestParam @NotBlank String location){
        return success(barMapper.toDtoList(barService.findByLocationContaining(location)));
    }


    @GetMapping("/bycate")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> getBarsByCategoryName(@RequestParam @NotBlank String category){
        return success(barMapper.toDtoList(barService.getBarsByCategoryName(category)));
    }

    @GetMapping("/bytag")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> getBarsByTagName(@RequestParam @NotBlank String tagName){
        return success(barMapper.toDtoList(barService.getBarsByTagName(tagName)));
    }

    //view count 증가
    @PostMapping("/increase-view-count")
    public ResponseEntity<Bar> increaseViewCount(@RequestParam Long barId) {
        Bar bar = barService.increaseViewCount(barId);
        if (bar != null) {
            return ResponseEntity.ok(bar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //구별 술집 인기 리스트 구현
    @GetMapping("/popularity")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> getTop3BarsByLocationSortedByHit(@RequestParam @NotBlank String location){
        return success(barMapper.toDtoList(barService.getTop3BarsByLocationSortedByHit(location)));
    }
    @GetMapping("/byrating")
    public HttpResponseEntity.ResponseResult<List<BarDTO>> getBarsByLocationSortedByRating(@RequestParam @NotBlank String location){
        return success(barMapper.toDtoList(barService.getBarsByLocationSortedByRating(location)));
    }
/*
    @ResponseBody
    @PostMapping(value="/imagetest",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long saveBar(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image, Bar bar) throws IOException{
        log.info("BarController.saveBar");
        System.out.println("BarController.saveBar");
        System.out.println("image = " + image);
        System.out.println("bar = " + bar);
        Long barId = barService.keepBar(image, bar);
        return barId;
    }
 */
}
