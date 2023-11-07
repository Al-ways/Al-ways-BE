package com.project.always.community.domain;

import com.project.always.security.oauth.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Community {
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
    private CommunityCategory communityCategory;

    private String title;
    private String content;
    private String status;
    private Date registration_date;
    private Date update_date;
    private Date delete_date;
    private Long hit;

    @OneToMany(mappedBy = "community")
    private List<CommunityFile> communityFiles = new ArrayList<>();

    public void updateUser(User user) {
        this.user = user;
    }
    @Builder
    public Community(Long id, User user, CommunityCategory communityCategory, String title, String content, String status, Date registration_date, Date update_date, Date delete_date, Long hit, List<CommunityFile> communityFiles) {
        this.id = id;
        this.user = user;
        this.communityCategory = communityCategory;
        this.title = title;
        this.content = content;
        this.status = status;
        this.registration_date = registration_date;
        this.update_date = update_date;
        this.delete_date = delete_date;
        this.hit = hit;
        this.communityFiles = communityFiles;
    }
}
