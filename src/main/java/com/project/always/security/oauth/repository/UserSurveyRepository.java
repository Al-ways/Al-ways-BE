package com.project.always.security.oauth.repository;

import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.entity.UserSurvey;
import com.project.always.survey.domain.Survey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
    Optional<UserSurvey> findByUserAndSurvey(User user, Survey survey);

    List<UserSurvey> findAllByUser(User user);
}
