package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.Review;
import com.project.always.bar.domain.UserBar;
import com.project.always.security.oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<UserBar> findByUserAndBar(User user, Bar bar);

}
