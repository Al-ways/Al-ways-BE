package com.project.always.bar.domain;

import com.project.always.bar.domain.Bar;
import com.project.always.security.oauth.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_bar_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @Builder
    public UserBar(User user, Bar bar){
        this.user = user;
        this.bar = bar;
    }
}
