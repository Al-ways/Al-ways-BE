package com.project.always.bar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BarCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; //카테고리번호
    private String name;//카테고리이름
/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private BarCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<BarCategory> child = new ArrayList<>();*/

    /*
    * public void addChildCategory(Category child) {
    this.child.add(child);
    child.setParent(this);
    }
    * */
    @OneToMany(mappedBy = "barCategory")
    private List<Bar> bars = new ArrayList<>();
}
