package com.trailsspain.api.mapper;

import com.trailsspain.api.dto.request.CreateRegionRequest;
import com.trailsspain.api.dto.request.UpdateRegionRequest;
import com.trailsspain.api.dto.response.RegionResponse;
import com.trailsspain.domain.model.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {

    public RegionResponse toResponse(Region region) {
        return RegionResponse.from(region);
    }

    public Region toEntity(CreateRegionRequest request) {
        return Region.builder()
                .name(request.name())
                .description(request.description())
                .imageUrl(request.imageUrl())
                .build();
    }

    public void updateEntity(UpdateRegionRequest request, Region region) {
        if (request.name() != null) {
            region.setName(request.name());
        }
        if (request.description() != null) {
            region.setDescription(request.description());
        }
        if (request.imageUrl() != null) {
            region.setImageUrl(request.imageUrl());
        }
    }
}
