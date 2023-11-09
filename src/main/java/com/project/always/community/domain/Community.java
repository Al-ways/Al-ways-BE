package com.project.always.community.domain;

import com.project.always.community.dto.CommunityRequestDTO;
import com.project.always.security.oauth.entity.User;
import com.project.always.utils.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id; //게시글번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Setter
    private CommunityCategory communityCategory;

    private String title;
    private String content;
    private String status;//필요할까?
//    private Date registration_date;
//    private Date update_date;
//    private Date delete_date;
    @Setter
    private Long hit;

    @OneToMany(mappedBy = "community",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityFile> communityFiles = new ArrayList<>();

    public void updateUser(User user) {
        this.user = user;
    }
    @Builder
    public Community(Long id, User user, String title, String content, Long hit) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.hit = hit;
    }



    public void update(CommunityRequestDTO communityRequestDTO) {
        this.content=communityRequestDTO.getContent();
        this.title=communityRequestDTO.getTitle();
    }
}
