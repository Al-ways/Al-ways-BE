package com.project.always.community.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.always.community.domain.CommunityCategory;
import com.project.always.security.oauth.entity.User;
import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityDTO {
    @JsonIgnore
    private CommunityCategory communityCategory;
    private Long cate_id;

    @JsonIgnore
    private User user;
    private String title;
    private String content;
    private Long hit;

    @Builder
    public CommunityDTO(CommunityCategory communityCategory,User user, String title, String content, Long hit) {
        this.communityCategory = communityCategory;
        this.cate_id = communityCategory.getId();
        this.user = user;
        this.title = title;
        this.content = content;
        this.hit = hit;
    }
}
