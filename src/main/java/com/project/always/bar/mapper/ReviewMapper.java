package com.project.always.bar.mapper;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.Review;
import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.dto.ReviewDTO;
import com.project.always.utils.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper  extends GenericMapper<ReviewDTO, Review> {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
