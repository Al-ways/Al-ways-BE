package com.project.always.bar.repository;

import com.project.always.bar.domain.TagBar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagBarRepository extends JpaRepository<TagBar, Long> {
    List<TagBar> findByTag_Id(Long tagId);
}
