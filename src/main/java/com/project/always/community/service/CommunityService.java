package com.project.always.community.service;

import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.community.domain.Community;
import com.project.always.community.domain.CommunityCategory;
import com.project.always.community.dto.CommunityRequestDTO;
import com.project.always.community.exception.CommunityNotFoundException;
import com.project.always.community.mapper.CommunityRequestMapper;
import com.project.always.community.repository.CommunityCategoryRepository;
import com.project.always.community.repository.CommunityRepository;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final CommunityRequestMapper communityRequestMapper;
    private final CommunityCategoryRepository communityCategoryRepository;

    @Transactional
    public Community insert(CommunityRequestDTO communityRequestDTO) {
        User user = userRepository.findById((communityRequestDTO.getUser_id()))
                .orElseThrow(()-> new BarNotFoundException("Could not found user id : " + communityRequestDTO.getUser_id()));

        Community community = communityRequestMapper.toEntity(communityRequestDTO);

        community.updateUser(user);
        community.setHit(0L);
        CommunityCategory communityCategory = communityCategoryRepository.findById(communityRequestDTO.getCategory_id())
                .orElseThrow(()-> new CommunityNotFoundException("Could not found category id : " + communityRequestDTO.getCategory_id()));
        community.setCommunityCategory(communityCategory);

        return communityRepository.save(community);
    }

    public void delete(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(()-> new CommunityNotFoundException("can not find community"));
        communityRepository.delete(community);
    }


    public void update(CommunityRequestDTO communityRequestDTO, Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(()-> new CommunityNotFoundException("can not find community"));
        community.update(communityRequestDTO);
    }

    public List<Community> getCommunityOrderByModifiedAtDesc() {
        return communityRepository.findAllByOrderByModifiedAtDesc();
    }

    public List<Community> getCommunityOrderByModifiedAtAsc() {
        return communityRepository.findAllByOrderByModifiedAtAsc();
    }

    public List<Community> getCommunityOrderByHit() {
        return communityRepository.findAllByOrderByHitDesc();
    }

    public Community getCommunityById(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new CommunityNotFoundException("can not find community"));
        community.setHit(community.getHit()+1);
        return community;
    }

    //좋아요순
    //조회순

}
