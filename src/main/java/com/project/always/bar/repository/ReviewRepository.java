package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.Review;
import com.project.always.bar.domain.UserBar;
import com.project.always.bar.dto.ReviewRequestDTO;
import com.project.always.security.oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
//    Optional<Review> findByUserAndBar(User user, Bar bar);

    List<Review> findByBar(Bar bar);

    List<Review> findByBarId(Long id);

    List<Review> findByUserId(Long id);
}
