package com.trailsspain.api.mapper;

import com.trailsspain.api.dto.request.RegisterRequest;
import com.trailsspain.api.dto.request.UpdateUserRequest;
import com.trailsspain.api.dto.response.UserResponse;
import com.trailsspain.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.from(user);
    }

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .email(request.email())
                .password(request.password())
                .fullName(request.fullName())
                .build();
    }

    public void updateEntity(UpdateUserRequest request, User user) {
        if (request.fullName() != null) {
            user.setFullName(request.fullName());
        }
        if (request.profileImageUrl() != null) {
            user.setProfileImageUrl(request.profileImageUrl());
        }
    }
}
