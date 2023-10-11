package com.project.always.bar.service;

import com.project.always.bar.domain.BarCategory;
import com.project.always.bar.repository.BarCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarCategoryService {
    private final BarCategoryRepository barCategoryRepository;

    public BarCategory findByName(String name){
        return barCategoryRepository.findByName(name);
    }
}
