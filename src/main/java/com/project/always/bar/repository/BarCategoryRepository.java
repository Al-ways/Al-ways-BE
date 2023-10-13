package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.BarCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarCategoryRepository  extends JpaRepository<BarCategory,Long> {
    BarCategory findByName(String name);
}
