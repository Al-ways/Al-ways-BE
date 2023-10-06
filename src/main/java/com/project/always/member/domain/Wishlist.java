package com.project.always.member.domain;

import com.project.always.bar.domain.Bar;
import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
