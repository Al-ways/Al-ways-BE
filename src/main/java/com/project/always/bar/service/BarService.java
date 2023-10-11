package com.project.always.bar.service;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.bar.repository.BarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BarService {
    private final BarRepository barRepository;
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
}
