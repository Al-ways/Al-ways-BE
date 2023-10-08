package com.project.always.member.domain;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;


    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

//    @Embedded
//    private ProfileUrl profileUrl;

    @Builder
    private Member(final String email, final String password, final String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @OneToMany(mappedBy = "member")
    private List<MemberBar> memberBars = new ArrayList<>();


}