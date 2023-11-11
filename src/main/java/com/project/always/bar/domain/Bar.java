package com.project.always.bar.domain;

import com.project.always.bar.elasticsearch.domain.BarDocument;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bar_id")
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

    @OneToMany(mappedBy = "bar")
    private List<UserBar> userBars = new ArrayList<>();

    @OneToMany(mappedBy = "bar")
    private List<Menu> menus = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BarCategory barCategory; //카테고리 번호

    @OneToMany(mappedBy = "bar")
    private List<TagBar> tagBars = new ArrayList<>();
    //조회수 올리는 코드
    public void increaseViewCount(Bar bar){
        bar.hit++;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static BarDocument from(Bar dto) {
        return BarDocument.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .location(dto.getLocation())
                .image(dto.getImage())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .tel(dto.getTel())
                .rating(dto.getRating())
                .build();
    }
}
