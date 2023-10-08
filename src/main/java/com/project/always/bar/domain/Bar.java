package com.project.always.bar.domain;

import com.project.always.member.domain.MemberBar;
import com.project.always.utils.BaseEntity;

import javax.persistence.*;

import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bar_id")
    private Long id; //술집번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BarCategory barCategory; //카테고리 번호
    private String title; //술집이름

    private String location; //술집위치

    private String tel; //전화번호

    private String lat; //위도
    private String log; //경도

    private String open_status; //영업시간
    private String group;//단체석여부
    private Long hit;//조회수

    @OneToMany(mappedBy = "bar")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "bar")
    private List<MemberBar> memberBars = new ArrayList<>();

    @OneToMany(mappedBy = "bar")
    private List<Menu> menus = new ArrayList<>();


}
