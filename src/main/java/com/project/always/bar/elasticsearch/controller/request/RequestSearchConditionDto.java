package com.project.always.bar.elasticsearch.controller.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class RequestSearchConditionDto {

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

}
