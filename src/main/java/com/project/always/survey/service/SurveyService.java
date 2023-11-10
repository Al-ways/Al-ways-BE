package com.project.always.survey.service;

import com.project.always.security.oauth.dto.response.SurveyResponseDto;
import com.project.always.survey.domain.Survey;
import com.project.always.survey.repository.SurveyRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public Optional<Survey> getSurveyInfo(Long id) {

        return surveyRepository.findById(id);
    }
}
