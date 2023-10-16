package com.project.always.bar.service;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.BarCategory;
import com.project.always.bar.domain.Tag;
import com.project.always.bar.domain.TagBar;
import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.bar.repository.BarCategoryRepository;
import com.project.always.bar.repository.BarRepository;
import com.project.always.bar.repository.TagBarRepository;
import com.project.always.bar.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BarService {
    private final BarRepository barRepository;
    private final BarCategoryRepository barCategoryRepository;
    private final TagRepository tagRepository;
    private final TagBarRepository tagBarRepository;
    @Transactional(readOnly = true)
    public List<Bar> findAll(){ return barRepository.findAll(); };
    @Transactional(readOnly = true)

    public Bar findByTitle(String barTitle){ return barRepository.findByTitle(barTitle); };
    @Transactional(readOnly = true)

    public Bar findById(Long barId){ return barRepository.findById(barId).orElseThrow(()-> new BarNotFoundException("Cannot find BAR"));};
    @Transactional(readOnly = true)

    public List<Bar> findByTitleContaining(String title) {
        return barRepository.findByTitleContaining(title);
    }

    public List<Bar> findByLocationContaining(String location) {
        return barRepository.findByLocationContaining(location);
    }
    public List<Bar> getBarsByCategoryName(String categoryName){
        BarCategory category = barCategoryRepository.findByName(categoryName);
        if(category == null){
            throw new BarNotFoundException("Cannot find that Category");
        }
        return category.getBars();
    }

    public List<Bar> getBarsByTagName(String tagName){
        Tag tag = tagRepository.findByName(tagName);//tag이름으로 해당 태그를 찾아옴
        if(tag == null){
            throw new BarNotFoundException("Cannot find that Tag");
        }

        List<TagBar> tagbars = tagBarRepository.findByTag_Id(tag.getId());
//        List<Bar> bars = new ArrayList<>();
//        for(int i=0;i<tagbars.size();i++)
//            bars.add(tagbars.get(i).getBar());
        List<Bar> bars = tagbars.stream()
                .map(TagBar::getBar)
                .collect(Collectors.toList());
        return bars;
    }

    public Bar increaseViewCount(Long barId) {
        Bar bar = barRepository.findById(barId).orElse(null);
        if (bar != null) {
            bar.increaseViewCount(bar);
            return barRepository.save(bar);
        }
        return null;
    }

    public List<Bar> getTop3BarsByLocationSortedByHit(String location) {
        return barRepository.findTop3ByLocationContainingOrderByHitDesc(location);
    }
}
