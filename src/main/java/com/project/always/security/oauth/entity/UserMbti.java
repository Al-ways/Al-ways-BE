package com.project.always.security.oauth.entity;

import static javax.persistence.FetchType.LAZY;

import com.project.always.mbti.domain.Mbti;
import com.project.always.security.oauth.entity.User;
import com.project.always.survey.domain.Survey;
import com.project.always.utils.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserMbti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mbti_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mbti_id")
    private Mbti mbti;

    @Builder
    private UserMbti(User user, Mbti mbti) {
        this.user = user;
        this.mbti = mbti;
    }
}