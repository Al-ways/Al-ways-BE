package com.project.always.bar.dto;

import com.project.always.bar.domain.Bar;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BarDTO {
    private Long id; //술집번호
    private String title; //술집이름
    private String location; //술집위치
    private Double rating;//술집 평점
    private String image; //대표이미지
    private String tel; //전화번호
    private String lat; //위도
    private String log; //경도

    private String open_status; //영업시간
    private String group_seat;//단체석여부
    private Long hit;//조회수


//    public BarDTO(Bar bar) {
//        this.id = bar.getId();
//        this.title = bar.getTitle();
//        this.location = bar.getLocation();
//        this.rating = bar.getRating();
//        this.image = bar.getImage();
//        this.tel = bar.getTel();
//        this.lat = bar.getLat();
//        this.log = bar.getLog();
//        this.open_status = bar.getOpen_status();
//        this.group_seat = bar.getGroup_seat();
//        this.hit = bar.getHit();
//    }

    @Builder
    public BarDTO(Long id, String title, String location, Double rating, String image, String tel, String lat, String log, String open_status, String group_seat, Long hit) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.rating = rating;
        this.image = image;
        this.tel = tel;
        this.lat = lat;
        this.log = log;
        this.open_status = open_status;
        this.group_seat = group_seat;
        this.hit = hit;
    }

    public static class ReviewResponseDTO {

    }
}
