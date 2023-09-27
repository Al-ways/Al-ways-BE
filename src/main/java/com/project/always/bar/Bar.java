<<<<<<<< HEAD:src/main/java/com/project/always/bar/domain/Bar.java
package com.project.always.bar.domain;

import com.project.always.utils.BaseEntity;
========
package com.project.always.bar;

>>>>>>>> dev:src/main/java/com/project/always/bar/Bar.java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.always.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bar_id")
    private Long id;

    private String title;

    private String location;

    private String tel;

    private String lat;
    private String log;

}
