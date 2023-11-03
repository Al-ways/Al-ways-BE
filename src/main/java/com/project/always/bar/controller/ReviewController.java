package com.project.always.bar.controller;

import com.project.always.bar.dto.ReviewDTO;
import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.bar.mapper.ReviewMapper;
import com.project.always.bar.mapper.ReviewRequestMapper;
import com.project.always.bar.service.ReviewService;
import com.project.always.utils.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.always.utils.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    //private final ReviewResponseMapper reviewResponseMapper;
    private  final ReviewRequestMapper reviewRequestMapper;
    private final ReviewMapper reviewMapper;
    @PostMapping("/create")
    public HttpResponseEntity.ResponseResult<String> insert(@RequestBody ReviewRequestDTO reviewRequestDTO){
        reviewService.insert(reviewRequestDTO);
        return success();
    }

    @DeleteMapping
    public HttpResponseEntity.ResponseResult<String> delete(@RequestParam Long reviewId){
        reviewService.delete(reviewId);
        return success();
    }

    @PutMapping("/{id}")
    public HttpResponseEntity.ResponseResult<String> update(@PathVariable Long id, @RequestBody ReviewRequestDTO reviewRequestDTO){
        reviewService.update(reviewRequestDTO,id);
        return success();
    }
    //술집별 리뷰 보기
    @GetMapping("/bar")
    public HttpResponseEntity.ResponseResult<List<ReviewDTO>> getReviewsByBarId(@RequestParam Long id) {
//        Bar bar = barRepository.findById(id)
//                .orElseThrow(() -> new BarNotFoundException("Bar not found with id: " + id));
//        List<Review> reviews = reviewService.getReviewsByBarId(id);
//        List<ReviewDTO> reviewDTOS = new ArrayList<>();
//        for (Review r:reviews) {
//            reviewDTOS.add(ReviewDTO.builder().id(r.getId()).select_rating(r.getSelect_rating()).content(r.getContent()).build());
//        }
//        return success(reviewDTOS);
        //해결한 오류: 순환참조  https://dev-coco.tistory.com/133
        return success(reviewMapper.toDtoList(reviewService.getReviewsByBarId(id)));
    }
    //내가 쓴 리뷰 확인
    @GetMapping("/user")
    public HttpResponseEntity.ResponseResult<List<ReviewDTO>> getReviewsByUserId(@RequestParam Long id){
        return success(reviewMapper.toDtoList(reviewService.getReviewsByUserId(id)));
    }
}
