package com.project.always.community.repository;

import com.project.always.bar.domain.Review;
import com.project.always.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {

}
