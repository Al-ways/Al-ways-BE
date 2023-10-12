package com.project.always.usermbti.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMbtiRepository extends JpaRepository<UserMbti, Long> {
}
