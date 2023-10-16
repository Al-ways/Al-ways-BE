package com.project.always.bar.repository;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface BarRepository extends JpaRepository<Bar,Long> {
    List<Bar> findAll();
    Bar findByTitle(String title);

    List<Bar> findByTitleContaining(String title);

    List<Bar> findByLocationContaining(String location);

    //@Query(value= "select * from bar where location like %:loc% order by hit desc limit 3", nativeQuery = true)
    List<Bar> findTop3ByLocationContainingOrderByHitDesc(String location);
}
