package com.project.always.bar.elasticsearch.domain;

import com.project.always.bar.dto.BarDTO;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "bar")
@Mapping(mappingPath = "elastic/bar-mapping.json")
@Setting(settingPath = "elastic/bar-setting.json")
public class BarDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id; //술집번호

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String location; //술집위치

    @Field(type = FieldType.Text)
    private String image; //대표이미지


    @Field(type = FieldType.Text)
    private String tel; //전화번호

    @Field(type = FieldType.Double)
    private Double rating;//술집 평점

    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    private LocalDateTime startDate;


    @Builder
    private BarDocument(Long id, String title, String location, String image, LocalDateTime startDate,
                       LocalDateTime endDate,
                       String tel, Double rating) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.image = image;
        this.startDate = startDate;
        this.tel = tel;
        this.rating = rating;
    }

    public static BarDocument of(BarDTO dto) {
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
