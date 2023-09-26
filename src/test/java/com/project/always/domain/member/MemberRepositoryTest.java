package com.project.always.domain.member;

import java.util.List;

import member.domain.Member;
import member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("회원 DB 조회")
    @Test
    void memberInfoReader() throws Exception{
        //given
        Member member = Member.builder()
                .email("cobi@naver.com")
                .password("cobi1234")
                .name("cobi")
                .build();
        memberRepository.save(member);

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Assertions.assertThat(memberList).hasSize(2)
                .extracting("email","password","name")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("test@naver.com", "test1234", "testId"),
                        Tuple.tuple("cobi@naver.com", "cobi1234", "cobi")
                );

    }

}