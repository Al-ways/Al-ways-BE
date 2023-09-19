package com.project.always.domain.bar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> { // type, id

}