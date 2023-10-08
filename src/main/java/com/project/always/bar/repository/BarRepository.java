package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface BarRepository extends JpaRepository<Bar,Long> {
    Bar findByTitle(String title);
}
