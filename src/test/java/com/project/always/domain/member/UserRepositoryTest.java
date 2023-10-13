package com.project.always.domain.member;

import static com.project.always.security.oauth.enums.AuthProvider.KAKAO;

import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.repository.UserRepository;
import java.util.List;

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
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("회원 DB 조회")
    // @Test
    void memberInfoReader() throws Exception{
        //given
        User user = User.builder()
                .email("cobi@naver.com")
                .password("cobi1234")
                .name("cobi")
                .authProvider(KAKAO)
                .oauth2Id("oauth2IdTest1")
                .build();
        userRepository.save(user);


        //when
        List<User> userList = userRepository.findAll();

        //then
        Assertions.assertThat(userList).hasSize(2)
                .extracting("email","password","name","authProvider")
                .containsExactlyInAnyOrder(
                        Tuple.tuple("test@naver.com", "test1234", "testId",KAKAO),
                        Tuple.tuple("cobi@naver.com", "cobi1234", "cobi",KAKAO)
                );

    }

}