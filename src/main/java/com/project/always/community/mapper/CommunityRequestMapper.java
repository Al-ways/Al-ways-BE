package com.project.always.community.mapper;

import com.project.always.community.domain.Community;
import com.project.always.community.dto.CommunityRequestDTO;
import com.project.always.utils.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommunityRequestMapper extends GenericMapper<CommunityRequestDTO, Community> {
        CommunityRequestMapper INSTANCE = Mappers.getMapper(CommunityRequestMapper.class);

}
