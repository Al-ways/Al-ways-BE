package com.project.always.community.repository;

import com.project.always.community.domain.Community;
import com.project.always.community.domain.CommunityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCategoryRepository extends JpaRepository<CommunityCategory, Long> {
}
