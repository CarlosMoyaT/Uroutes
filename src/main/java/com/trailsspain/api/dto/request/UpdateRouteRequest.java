package com.trailsspain.api.dto.request;

import com.trailsspain.domain.model.Route;
import jakarta.validation.constraints.Positive;

public record UpdateRouteRequest(
    String name,
    String description,
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
    Long regionId
) {}
