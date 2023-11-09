package com.project.always.community.repository;

import com.project.always.bar.domain.Review;
import com.project.always.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findAllByOrderByModifiedAtDesc(); // 수정시간 최신순을 정렬해주셈

    List<Community> findAllByOrderByModifiedAtAsc();

    List<Community> findAllByOrderByHitDesc();
}
