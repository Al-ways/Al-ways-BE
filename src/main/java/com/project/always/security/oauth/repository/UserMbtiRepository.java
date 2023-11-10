package com.project.always.security.oauth.repository;

import com.project.always.mbti.domain.Mbti;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserMbti;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMbtiRepository extends JpaRepository<UserMbti, Long> {
    Optional<Object> findByUserAndMbti(User user, Mbti mbti);
}
