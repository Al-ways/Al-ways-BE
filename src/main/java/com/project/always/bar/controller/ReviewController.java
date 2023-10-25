package com.project.always.bar.controller;

import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.bar.mapper.ReviewRequestMapper;
import com.project.always.bar.mapper.ReviewResponseMapper;
import com.project.always.bar.service.ReviewService;
import com.project.always.utils.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewResponseMapper reviewResponseMapper;
    private  final ReviewRequestMapper reviewRequestMapper;

    @PostMapping("/create")
    public HttpResponseEntity.ResponseResult<BarDTO.ReviewResponseDTO> insert(@RequestBody ReviewRequestDTO reviewRequestDTO){
        ;
        return success(reviewResponseMapper.toDto(reviewService.insert(reviewRequestDTO)));
    }

    @DeleteMapping
    public HttpResponseEntity.ResponseResult<?> delete(@RequestParam Long reviewId){
        return success(reviewRequestMapper.toDto(reviewService.delete(reviewId)));
    }

    @PutMapping("/{id}")
    public HttpResponseEntity.ResponseResult<BarDTO.ReviewResponseDTO> update(@PathVariable Long id, @RequestBody ReviewRequestDTO reviewRequestDTO){

        return success(reviewResponseMapper.toDto(reviewService.update(reviewRequestDTO,id)));
    }
}
