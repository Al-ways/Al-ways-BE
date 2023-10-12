package com.project.always.bar.service;
import com.project.always.bar.domain.Bar;
import com.project.always.bar.domain.UserBar;
import com.project.always.bar.error.exception.BarNotFoundException;
import com.project.always.bar.dto.UserBarRequestDTO;
import com.project.always.bar.error.exception.DuplicateResourceException;
import com.project.always.bar.repository.BarRepository;
import com.project.always.bar.repository.UserBarRepository;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserBarService {
    private final UserBarRepository userBarRepository;
    private final UserRepository userRepository;
    private final BarRepository barRepository;

    @Transactional
    public void insert(UserBarRequestDTO userBarRequestDTO)throws  Exception{
        User user = userRepository.findById((userBarRequestDTO.getUserId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found user id : " + userBarRequestDTO.getUserId()));

        Bar bar = barRepository.findById((userBarRequestDTO.getBarId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found bar id : " + userBarRequestDTO.getBarId()));

        // 이미 좋아요되어있으면 에러 반환
        if (userBarRepository.findByUserAndBar(user, bar).isPresent()){
            //TODO 409에러로 변경
            throw new DuplicateResourceException("already exist data by user id :" + user.getId() + " ,"
                    + "bar id : " + bar.getId());
        }

        UserBar userBar = UserBar.builder()
                .bar(bar)
                .user(user)
                .build();

        userBarRepository.save(userBar);
        //Todo :: bar like count
       /*barRepository.addLikeCount(bar);*/
    }
    @Transactional
    public void delete(UserBarRequestDTO userBarRequestDTO) {

        User user = userRepository.findById((userBarRequestDTO.getUserId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found user id : " + userBarRequestDTO.getUserId()));

        Bar bar = barRepository.findById((userBarRequestDTO.getBarId()))
                .orElseThrow(()-> new BarNotFoundException("Could not found bar id : " + userBarRequestDTO.getBarId()));

        UserBar userBar= userBarRepository.findByUserAndBar(user, bar)
                .orElseThrow(() -> new BarNotFoundException("Could not found userBar id"));

        userBarRepository.delete(userBar);
        //Todo :: bar like count
        /*barRepository.subLikeCount(bar);*/
    }
}
