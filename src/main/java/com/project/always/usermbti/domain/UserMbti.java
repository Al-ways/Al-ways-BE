package com.project.always.usermbti.domain;

import static javax.persistence.FetchType.LAZY;

import com.project.always.mbti.domain.Mbti;
import com.project.always.security.oauth.entity.User;
import com.project.always.survey.domain.Survey;
import com.project.always.utils.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserMbti extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mbti_id")
    private Mbti mbti;

    public UserMbti(User user, Mbti mbti) {
        this.user = user;
        this.mbti = mbti;
    }
}