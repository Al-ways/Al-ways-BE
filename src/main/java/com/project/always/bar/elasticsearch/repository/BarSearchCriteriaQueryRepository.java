package com.project.always.bar.elasticsearch.repository;

import com.project.always.bar.elasticsearch.controller.request.RequestSearchConditionDto;
import com.project.always.bar.elasticsearch.domain.BarDocument;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class BarSearchCriteriaQueryRepository {

    private final ElasticsearchOperations operations;

    public List<BarDocument> findByCriteriaCondition(RequestSearchConditionDto dto) {
        CriteriaQuery query = createConditionCriteriaQuery(dto);

        SearchHits<BarDocument> search = operations.search(query, BarDocument.class);
        return search
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    private CriteriaQuery createConditionCriteriaQuery(RequestSearchConditionDto dto){
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        if(dto == null){
            return query;
        }

        if(dto.getId() != null){
            query.addCriteria(Criteria.where("id").is(dto.getId()));
        }

        if(StringUtils.hasText(dto.getTitle())){
            query.addCriteria(Criteria.where("title").is(dto.getTitle()));
        }

        if(StringUtils.hasText(dto.getLocation())){
            query.addCriteria(Criteria.where("location").is(dto.getLocation()));
        }

        if(StringUtils.hasText(dto.getImage())){
            query.addCriteria(Criteria.where("image").is(dto.getImage()));
        }

        if(StringUtils.hasText(dto.getTel())){
            query.addCriteria(Criteria.where("tel").is(dto.getTel()));
        }

        if(dto.getRating() != null && dto.getRating() > 0L){
            query.addCriteria(Criteria.where("rating").is(dto.getRating()));
        }

        return query;
    }
}
