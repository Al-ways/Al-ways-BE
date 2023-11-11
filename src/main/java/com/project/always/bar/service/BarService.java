package com.project.always.bar.service;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.BarCategory;
import com.project.always.bar.domain.Tag;
import com.project.always.bar.domain.TagBar;
import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.bar.repository.BarCategoryRepository;
import com.project.always.bar.repository.BarRepository;
import com.project.always.bar.repository.TagBarRepository;
import com.project.always.bar.repository.TagRepository;
import com.project.always.utils.S3Uploader;
import com.project.always.bar.elasticsearch.controller.response.ResponseBarDto;
import com.project.always.bar.elasticsearch.repository.BarSearchCriteriaQueryRepository;
import com.project.always.bar.elasticsearch.repository.BarSearchRepository;
import com.project.always.bar.elasticsearch.controller.request.RequestSearchConditionDto;
import com.project.always.bar.elasticsearch.domain.BarDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BarService {
    private final BarRepository barRepository;
    private final BarCategoryRepository barCategoryRepository;
    private final TagRepository tagRepository;
    private final TagBarRepository tagBarRepository;

    private final BarSearchRepository barSearchRepository;
    private final BarSearchCriteriaQueryRepository criteriaQueryRepository;


    @Autowired
    private S3Uploader s3Uploader;

    public List<Bar> findAll(){ return barRepository.findAll(); };


    public Bar findByTitle(String barTitle){ return barRepository.findByTitle(barTitle); };


    public Bar findById(Long barId){ return barRepository.findById(barId).orElseThrow(()-> new BarNotFoundException("Cannot find BAR"));};


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

    public List<Bar> getBarsByLocationSortedByRating(String location) {
        return barRepository.findByLocationContainingOrderByRatingDesc(location);
    }
    /*
    @Transactional
    public Long keepBar(MultipartFile image, Bar bar) throws IOException {
        if(!image.isEmpty()) {
            String storedFileName = s3Uploader.upload(image,"images");
            bar.setImage(storedFileName);
        }
        Bar savedBar = barRepository.save(bar);
        return savedBar.getId();
    }*/

    // barSearch
    public void saveAll(List<BarDTO> info) {
        List<BarDocument> users = info.stream()
                .map(BarDocument::of)
                .collect(Collectors.toList());

//        barRepository.findAll().stream()
//                        .map(BarDocument::of)
//                                .collect(Collectors.toList());

        barSearchRepository.saveAll(users);


    }

    @Transactional
    public void saveSearchAll() {
        List<BarDocument> collect = barRepository.findAll().stream()
                .map(Bar::from)
                .collect(Collectors.toList());

        barSearchRepository.saveAll(collect);
    }

    public List<ResponseBarDto> findBySearchTitle(String title) {
        return transInfo(barSearchRepository.findByTitle(title));
    }

    public List<ResponseBarDto> findBySearchLocation(String location) {
        return transInfo(barSearchRepository.findByLocation(location));
    }

    public List<ResponseBarDto> findByCriteriaCondition(RequestSearchConditionDto dto){
        return transInfo(criteriaQueryRepository.findByCriteriaCondition(dto));
    }


    private List<ResponseBarDto> transInfo(List<BarDocument> bars) {
        return bars.stream()
                .map(ResponseBarDto::of)
                .collect(Collectors.toList());
    }
}
