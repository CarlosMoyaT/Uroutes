package com.trailsspain.api.dto.response;

import com.trailsspain.domain.model.User;
import java.time.LocalDateTime;

public record UserResponse(
    Long id,
    String email,
    String fullName,
    String profileImageUrl,
    LocalDateTime createdAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getFullName(),
            user.getProfileImageUrl(),
            user.getCreatedAt()
        );
    }
}
