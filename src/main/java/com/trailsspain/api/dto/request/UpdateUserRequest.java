package com.trailsspain.api.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    String fullName,
    
    String profileImageUrl
) {}
