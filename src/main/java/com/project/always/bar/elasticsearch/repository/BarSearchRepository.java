package com.project.always.bar.elasticsearch.repository;

import com.project.always.bar.elasticsearch.domain.BarDocument;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BarSearchRepository extends ElasticsearchRepository<BarDocument, Long> {
    List<BarDocument> findByTitle(String title);
    List<BarDocument> findByLocation(String location);

}
