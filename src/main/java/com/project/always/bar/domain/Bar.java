package com.project.always.bar.domain;

import com.project.always.utils.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bar_id")
    private Long id; //술집번호

    private Long category_id; //카테고리 번호 -> mapping필요
    private String title; //술집이름

    private String location; //술집위치

    private String tel; //전화번호

    private String lat; //위도
    private String log; //경도

    private String open_status; //영업시간
    private String group;//단체석여부
    private Long hit;//조회수

}
