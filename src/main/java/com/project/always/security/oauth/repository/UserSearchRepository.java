package com.project.always.security.oauth.repository;

import com.project.always.security.oauth.entity.UserSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSearchRepository extends JpaRepository<UserSearch, Long> {
}
