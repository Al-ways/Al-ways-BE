package com.project.always.bar.domain;

import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id; //메뉴번호

    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar; //술집번호

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MenuCategory menuCategory;//카테고리 번호

    private String name;//메뉴이름
    private Long price;//가격

    @OneToOne
    @JoinColumn(name = "file_id")
    private Image image;//파일 번호




}