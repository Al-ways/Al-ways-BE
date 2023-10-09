package com.project.always.bar.service;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.repository.BarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BarService {
    private final BarRepository barRepository;
    @Transactional(readOnly = true)
    public List<Bar> findAll(){ return barRepository.findAll(); };

    public Bar findByTitle(String barTitle){ return barRepository.findByTitle(barTitle); };


}
