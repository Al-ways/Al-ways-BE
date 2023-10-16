package com.project.always.bar.mapper;

import com.project.always.bar.domain.Review;
import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.utils.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewResponseMapper extends GenericMapper<BarDTO.ReviewResponseDTO, Review> {
    ReviewResponseMapper INSTANCE = Mappers.getMapper(ReviewResponseMapper.class);
}