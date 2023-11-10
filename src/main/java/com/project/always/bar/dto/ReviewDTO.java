package com.project.always.bar.dto;

import com.project.always.bar.domain.Bar;
import com.project.always.security.oauth.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDTO {
    private Long id;
    private double select_rating;
    private String content;
    @Builder
    public ReviewDTO(Long id, double select_rating, String content) {
        this.id = id;
        this.select_rating = select_rating;
        this.content = content;
    }
}
