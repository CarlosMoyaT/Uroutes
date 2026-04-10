package com.trailsspain.api.dto.request;

import com.trailsspain.domain.model.Route;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateRouteRequest(
    @NotBlank(message = "Name is required")
    String name,
    
    String description,
    
    @NotNull(message = "Difficulty is required")
    Route.Difficulty difficulty,
    
    @Positive(message = "Distance must be positive")
    Double distanceKm,
    
    @Positive(message = "Elevation gain must be positive")
    Integer elevationGainM,
    
    @Positive(message = "Estimated duration must be positive")
    Integer estimatedDurationMin,
    
    String gpsCoordinates,
    
    String startPoint,
    
    String endPoint,
    
    String coverImageUrl,
    
    String mapImageUrl,
    
    @NotNull(message = "Author ID is required")
    Long authorId,
    
    Long regionId
) {}
