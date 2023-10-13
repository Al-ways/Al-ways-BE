package com.project.always.bar.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBarRequestDTO {
    private Long barId;
    private Long userId;

    public UserBarRequestDTO(Long barId, Long userId) {
        this.barId = barId;
        this.userId = userId;
    }
}
