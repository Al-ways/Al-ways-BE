package com.project.always.bar.domain;

import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id; //파일번호

    private String name; //파일이름
    private String org_name; //파일 원본 이름

    private Long menu_id; //메뉴번호
}
