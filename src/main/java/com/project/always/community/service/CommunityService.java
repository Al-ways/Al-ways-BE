package com.project.always.community.service;

import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.community.domain.Community;
import com.project.always.community.dto.CommunityRequestDTO;
import com.project.always.community.mapper.CommunityRequestMapper;
import com.project.always.community.repository.CommunityRepository;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final CommunityRequestMapper communityRequestMapper;
    @Transactional
    public Community insert(CommunityRequestDTO communityRequestDTO) {
        User user = userRepository.findById((communityRequestDTO.getUserId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found user id : " + communityRequestDTO.getUserId()));
        Community community = communityRequestMapper.toEntity(communityRequestDTO);

        community.updateUser(user);
        return communityRepository.save(community);
    }
}
