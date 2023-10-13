package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.UserBar;
import com.project.always.security.oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface UserBarRepository extends JpaRepository<UserBar, Long> {
    Optional<UserBar> findByUserAndBar(User user, Bar bar);
}
