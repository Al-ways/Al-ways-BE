package com.project.always.community.domain;

import com.project.always.bar.domain.TagBar;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id; //이미지번호

    private String name; //이미지이름
    private String org_name; //파일 원본 이름

    @OneToMany(mappedBy = "file")
    private List<CommunityFile> communityFiles = new ArrayList<>();
}
