package com.project.always.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}