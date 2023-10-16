package com.project.always.bar.mapper;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.dto.BarDTO;
import com.project.always.utils.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BarMapper extends GenericMapper<BarDTO, Bar> {
    BarMapper INSTANCE = Mappers.getMapper(BarMapper.class);

}
