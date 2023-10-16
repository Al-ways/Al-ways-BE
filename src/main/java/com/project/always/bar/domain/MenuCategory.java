package com.project.always.bar.domain;

import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_id")
    private Long id; //카테고리번호

    private String name;//카테고리명
    private String grade;//카테고리등급

    @OneToMany(mappedBy = "menuCategory")
    List<Menu> menus = new ArrayList<>();
}
