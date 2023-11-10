package com.project.always.security.oauth.controller;

import com.project.always.bar.service.UserBarService;
import com.project.always.security.oauth.dto.ProfileImageDto;
import com.project.always.security.oauth.dto.request.UserNameRequestDto;
import com.project.always.security.oauth.dto.request.UserSurveyRequestDto;
import com.project.always.security.oauth.dto.response.UserMyPageResponseDto;
import com.project.always.security.oauth.dto.response.UserResponseDto;
import com.project.always.security.oauth.entity.User;
import com.project.always.security.oauth.oauth2.UserPrincipal;
import com.project.always.security.oauth.service.UserMbtiService;
import com.project.always.security.oauth.service.UserService;
import com.project.always.security.oauth.service.UserSurveyService;
import com.project.always.utils.S3Service;
import com.project.always.utils.SuccessResponse;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class OauthControllerApi {

    private final UserService userService;

    private final UserMbtiService userMbtiService;
    private final UserBarService userBarService;
    private final S3Service s3Service;

    private final UserSurveyService userSurveyService;

    @GetMapping("/kakao/login")
    public ResponseEntity<String> kakaoLogin(@RequestParam String token) {
        return ResponseEntity.ok(token);
    }

    @PutMapping("/user/name")
    public ResponseEntity<SuccessResponse> modifyName(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody @Valid UserNameRequestDto userNameuserNameRequestDto) {

        userService.modifyName(userPrincipal.getUser().getId(), userNameuserNameRequestDto);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("member.modifyName.success")
                        .data("data")
                        .build());
    }

    @GetMapping("/profile")
    public ResponseEntity<SuccessResponse> getProfileImage(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        String profileImage = userService.getProfileImage(userPrincipal.getUser().getId())
                .orElseThrow(() -> new RuntimeException("member.notFoundProfileImage.exception"));

        return ResponseEntity.ok(SuccessResponse.builder()
                .message("get.user.profileImage.success")
                .data(new ProfileImageDto(profileImage))
                .build());
    }

    @GetMapping("/mypage")
    public ResponseEntity<SuccessResponse> getUserInfoMypage(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        UserMyPageResponseDto userInfoMypage = userService.getUserInfoMypage(
                userPrincipal.getUser().getId());

        return ResponseEntity.ok(SuccessResponse.builder()
                .message("get.user.mypage.success")
                .data(userInfoMypage)
                .build());
    }

    @PostMapping("/profile/modify")
    public ResponseEntity<SuccessResponse> postProfileImage(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam("image") MultipartFile imageFile) {

        String imageUrl;

        try {
            imageUrl = s3Service.upload(imageFile, "profile");
        } catch (Exception e) {
            throw new RuntimeException("user.uploadProfileImage.failure");
        }
        userService.putProfileImage(userPrincipal.getUser().getId(), imageUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                SuccessResponse.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("post.user.profileImage.update.success")
                        .data(new ProfileImageDto(imageUrl))
                        .build());
    }

//    @GetMapping("/{authProvider}/login")
//    public String oauthLogin(@AuthenticationPrincipal UserPrincipal userPrincipal,
//                             @PathVariable AuthProvider authProvider) {
//        return userPrincipal.getName();
//    }

    @GetMapping("/callback/kakao")
    public String oauthKakaoRedirectUrl(@RequestParam UserResponseDto userResponseDto) {

        return userResponseDto.getAccessToken();
    }
}