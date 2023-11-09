package com.project.always.bar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.security.oauth.entity.User;
import com.project.always.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bar_id")
    @Setter
    //@JsonIgnore
    private Bar bar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    //@JsonIgnore
    private User user;

    private double select_rating;
    private String content;

    public Review update(ReviewRequestDTO reviewRequestDTO) {
        this.content= reviewRequestDTO.getContent();
        this.select_rating=reviewRequestDTO.getSelect_rating();
        return this;
    }

    @Builder
    public Review(Long id, Bar bar, User user, int select_rating, String content) {
        this.id = id;
        this.bar = bar;
        this.user = user;
        this.select_rating = select_rating;
        this.content = content;
    }


}