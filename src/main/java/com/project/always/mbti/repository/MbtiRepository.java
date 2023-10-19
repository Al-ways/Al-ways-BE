package com.project.always.mbti.repository;

import com.project.always.mbti.domain.Mbti;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiRepository extends JpaRepository<Mbti, Long> {

    Optional<Mbti> findByPattern(String pattern);
}
