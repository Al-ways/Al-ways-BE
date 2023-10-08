package com.project.always.bar.service;

import com.project.always.bar.repository.BarRepository;
import com.project.always.bar.repository.UserBarRepository;
import com.project.always.security.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBarService {
    private final UserBarRepository userBarRepository;
    private final UserRepository userRepository;
    private final BarRepository barRepository;

}
