package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface BarRepository extends JpaRepository<Bar,Long> {
    List<Bar> findAll();
    Bar findByTitle(String title);

    List<Bar> findByTitleContaining(String title);
}
