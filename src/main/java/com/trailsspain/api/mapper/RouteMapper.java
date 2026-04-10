package com.trailsspain.api.mapper;

import com.trailsspain.api.dto.request.CreateRouteRequest;
import com.trailsspain.api.dto.request.UpdateRouteRequest;
import com.trailsspain.api.dto.response.RouteResponse;
import com.trailsspain.domain.model.Route;
import com.trailsspain.domain.repository.RegionRepository;
import com.trailsspain.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RouteMapper {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public RouteResponse toResponse(Route route) {
        return RouteResponse.from(route);
    }

    public Route toEntity(CreateRouteRequest request) {
        Route route = Route.builder()
                .name(request.name())
                .description(request.description())
                .difficulty(request.difficulty())
                .distanceKm(request.distanceKm())
                .elevationGainM(request.elevationGainM())
                .estimatedDurationMin(request.estimatedDurationMin())
                .gpsCoordinates(request.gpsCoordinates())
                .startPoint(request.startPoint())
                .endPoint(request.endPoint())
                .coverImageUrl(request.coverImageUrl())
                .mapImageUrl(request.mapImageUrl())
                .build();

        if (request.authorId() != null) {
            userRepository.findById(request.authorId()).ifPresent(route::setAuthor);
        }
        if (request.regionId() != null) {
            regionRepository.findById(request.regionId()).ifPresent(route::setRegion);
        }

        return route;
    }

    public void updateEntity(UpdateRouteRequest request, Route route) {
        if (request.name() != null) {
            route.setName(request.name());
        }
        if (request.description() != null) {
            route.setDescription(request.description());
        }
        if (request.difficulty() != null) {
            route.setDifficulty(request.difficulty());
        }
        if (request.distanceKm() != null) {
            route.setDistanceKm(request.distanceKm());
        }
        if (request.elevationGainM() != null) {
            route.setElevationGainM(request.elevationGainM());
        }
        if (request.estimatedDurationMin() != null) {
            route.setEstimatedDurationMin(request.estimatedDurationMin());
        }
        if (request.gpsCoordinates() != null) {
            route.setGpsCoordinates(request.gpsCoordinates());
        }
        if (request.startPoint() != null) {
            route.setStartPoint(request.startPoint());
        }
        if (request.endPoint() != null) {
            route.setEndPoint(request.endPoint());
        }
        if (request.coverImageUrl() != null) {
            route.setCoverImageUrl(request.coverImageUrl());
        }
        if (request.mapImageUrl() != null) {
            route.setMapImageUrl(request.mapImageUrl());
        }
        if (request.regionId() != null) {
            regionRepository.findById(request.regionId()).ifPresent(route::setRegion);
        }
    }
}
