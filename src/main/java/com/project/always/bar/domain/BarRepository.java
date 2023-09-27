package com.project.always.bar.domain;

import com.project.always.bar.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> { // type, id

}