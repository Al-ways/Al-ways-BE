package com.project.always.security.oauth.service;

import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.oauth2.UserPrincipal;
import com.project.always.security.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
        return new UserPrincipal(user);
    }
}
