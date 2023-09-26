package com.project.always.security.oauth.repository;

import com.project.always.security.oauth.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { // type, id

    Optional<User> findByEmail(String email);
}