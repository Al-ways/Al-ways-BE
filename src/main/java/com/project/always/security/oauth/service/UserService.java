package com.project.always.security.oauth.service;

import com.project.always.security.oauth.dto.request.UserNameRequestDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void modifyName(Long id, UserNameRequestDto userNameRequestDto) {
        Optional<User> userInfo = userRepository.findById(id);
        userInfo.ifPresent(m -> m.setName(userNameRequestDto.getName()));
    }

    public Optional<String> getProfileImage(Long id) {
        return userRepository.findById(id).map(User::getProfileImage);
    }

}

