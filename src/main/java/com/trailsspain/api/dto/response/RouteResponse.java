package com.trailsspain.api.dto.response;

import com.trailsspain.domain.model.Route;
import java.time.LocalDateTime;

public record RouteResponse(
    Long id,
    String name,
    String description,
    Route.Difficulty difficulty,
    Double distanceKm,
    Integer elevationGainM,
    Integer estimatedDurationMin,
    String gpsCoordinates,
    String startPoint,
    String endPoint,
    String coverImageUrl,
    String mapImageUrl,
    UserSummaryResponse author,
    RegionSummaryResponse region,
    Double averageRating,
    Integer totalReviews,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public record UserSummaryResponse(Long id, String fullName) {
        public static UserSummaryResponse from(Route route) {
            return route.getAuthor() != null 
                ? new UserSummaryResponse(route.getAuthor().getId(), route.getAuthor().getFullName())
                : null;
        }
    }

    public record RegionSummaryResponse(Long id, String name) {
        public static RegionSummaryResponse from(Route route) {
            return route.getRegion() != null
                ? new RegionSummaryResponse(route.getRegion().getId(), route.getRegion().getName())
                : null;
        }
    }

    public static RouteResponse from(Route route) {
        return new RouteResponse(
            route.getId(),
            route.getName(),
            route.getDescription(),
            route.getDifficulty(),
            route.getDistanceKm(),
            route.getElevationGainM(),
            route.getEstimatedDurationMin(),
            route.getGpsCoordinates(),
            route.getStartPoint(),
            route.getEndPoint(),
            route.getCoverImageUrl(),
            route.getMapImageUrl(),
            UserSummaryResponse.from(route),
            RegionSummaryResponse.from(route),
            route.getAverageRating(),
            route.getTotalReviews(),
            route.getCreatedAt(),
            route.getUpdatedAt()
        );
    }
}
