package com.trailsspain.api.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateRegionRequest(
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String name,
    
    String description,
    String imageUrl
) {}
