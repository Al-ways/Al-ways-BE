package com.project.always.community.dto;

import com.project.always.security.oauth.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Getter
@NoArgsConstructor
public class CommunityRequestDTO {
    private Long category_id;
    private Long userId;
    private String title;
    private String content;

    public CommunityRequestDTO(Long category_id, Long userId, String title, String content) {
        this.category_id = category_id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
