package com.project.always.bar.domain;

import com.project.always.security.oauth.entity.User;
import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id; //이미지번호

    private String name; //이미지이름
    private String org_name; //파일 원본 이름
    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;


}
