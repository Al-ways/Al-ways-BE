package com.project.always.bar.repository;

import com.project.always.bar.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String tagName);
}
