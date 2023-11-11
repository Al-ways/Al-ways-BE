package com.project.always.bar.elasticsearch.controller.response;

import com.project.always.bar.elasticsearch.domain.BarDocument;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

@Data
public class ResponseBarDto {
    @Nullable
    private Long id;

    @Nullable
    private String title;

    @Nullable
    private String location;

    @Nullable
    private String image;


    @Nullable
    private String tel;

    @Nullable
    private Double rating;

    ResponseBarDto(){}

    public static ResponseBarDto of(BarDocument bar) {

        ResponseBarDto dto = new ResponseBarDto();

        System.out.println(bar.getTitle());
        System.out.println(bar.getLocation());

        if(dto.getId() != null){
            dto.id = bar.getId();
        }

        if(StringUtils.hasText(dto.getTitle())){
            dto.title = bar.getTitle();
        }

        if(StringUtils.hasText(dto.getLocation())){
            dto.location = bar.getLocation();
        }

        if(StringUtils.hasText(dto.getImage())){
            dto.image = bar.getImage();
        }

        if(StringUtils.hasText(dto.getTel())){
            dto.tel = bar.getTel();
        }

        if(dto.getRating() != null && dto.getRating() > 0L){
            dto.rating = bar.getRating();
        }

        return dto;
    }
}
