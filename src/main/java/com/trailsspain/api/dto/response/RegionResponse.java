package com.trailsspain.api.dto.response;

import com.trailsspain.domain.model.Region;
import java.time.LocalDateTime;

public record RegionResponse(
    Long id,
    String name,
    String description,
    String imageUrl,
    Integer routeCount,
    LocalDateTime createdAt
) {
    public static RegionResponse from(Region region) {
        return new RegionResponse(
            region.getId(),
            region.getName(),
            region.getDescription(),
            region.getImageUrl(),
            region.getRoutes() != null ? region.getRoutes().size() : 0,
            region.getCreatedAt()
        );
    }
}
