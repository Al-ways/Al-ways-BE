package com.project.always.bar.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDTO {
    private Long barId;
    private Long userId;
    private int select_rating;
    private String content;

    public ReviewRequestDTO(Long barId, Long userId, int select_rating, String content) {
        this.barId = barId;
        this.userId = userId;
        this.select_rating = select_rating;
        this.content = content;
    }
}
