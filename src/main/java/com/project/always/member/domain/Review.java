package com.project.always.member.domain;

import com.project.always.bar.domain.Bar;
import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
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

    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int select_rating;
    private String content;
    private Date date;
}
