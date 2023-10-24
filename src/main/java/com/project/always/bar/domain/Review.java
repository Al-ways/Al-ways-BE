package com.project.always.bar.domain;

import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.security.oauth.entity.User;
import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int select_rating;
    private String content;
    private Date date;
    public void updateBar(Bar bar) {
        this.bar = bar;
    }

    public void updateUser(User user) {
        this.user = user;
    }

    public Review update(ReviewRequestDTO reviewRequestDTO) {
        this.content= content;
        this.select_rating=select_rating;
        return this;
    }

    @Builder
    public Review(Long id, Bar bar, User user, int select_rating, String content, Date date) {
        this.id = id;
        this.bar = bar;
        this.user = user;
        this.select_rating = select_rating;
        this.content = content;
        this.date = date;
    }


}