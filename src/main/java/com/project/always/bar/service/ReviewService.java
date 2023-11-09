package com.project.always.bar.service;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.Review;
import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.bar.mapper.ReviewRequestMapper;
import com.project.always.bar.repository.BarRepository;
import com.project.always.bar.repository.ReviewRepository;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BarRepository barRepository;
    private final ReviewRequestMapper reviewRequestMapper;
    @Transactional
    public Review insert(ReviewRequestDTO reviewRequestDTO) {
        User user = userRepository.findById((reviewRequestDTO.getUserId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found user id : " + reviewRequestDTO.getUserId()));

        Bar bar = barRepository.findById((reviewRequestDTO.getBarId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found bar id : " + reviewRequestDTO.getBarId()));
        Review review = reviewRequestMapper.toEntity(reviewRequestDTO);

        review.setUser(user);
        review.setBar(bar);
        return reviewRepository.save(review);
    }


    public Review delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new BarNotFoundException("Could not found bar"));//to do exception 바꾸기
        reviewRepository.delete(review);
        return review;
    }

    @Transactional
    public Review update(ReviewRequestDTO reviewRequestDTO, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new BarNotFoundException("Could not found bar"));

        return review.update(reviewRequestDTO);
    }

    public List<Review> getReviewsByBar(Bar bar) {
        return reviewRepository.findByBar(bar);
    }

    public List<Review> getReviewsByBarId(Long id) {
        return reviewRepository.findByBarId(id);
    }

    public List<Review> getReviewsByUserId(Long id) {
        return reviewRepository.findByUserId(id);
    }
}
