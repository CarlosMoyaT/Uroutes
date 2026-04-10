package com.trailsspain.application.service;

import com.trailsspain.api.dto.request.CreateRouteRequest;
import com.trailsspain.api.dto.request.UpdateRouteRequest;
import com.trailsspain.api.dto.response.RouteResponse;
import com.trailsspain.domain.model.Route;
import com.trailsspain.domain.repository.RouteRepository;
import com.trailsspain.api.mapper.RouteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Transactional(readOnly = true)
    public RouteResponse findById(Long id) {
        return routeRepository.findById(id)
                .map(routeMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<RouteResponse> findAll(Pageable pageable) {
        return routeRepository.findAll(pageable).map(routeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<RouteResponse> findByRegion(Long regionId, Pageable pageable) {
        return routeRepository.findByRegionId(regionId, pageable).map(routeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<RouteResponse> findByDifficulty(Route.Difficulty difficulty, Pageable pageable) {
        return routeRepository.findByDifficulty(difficulty, pageable).map(routeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<RouteResponse> searchByKeyword(String keyword, Pageable pageable) {
        return routeRepository.searchByKeyword(keyword, pageable).map(routeMapper::toResponse);
    }

    public RouteResponse create(CreateRouteRequest request) {
        Route route = routeMapper.toEntity(request);
        Route saved = routeRepository.save(route);
        return routeMapper.toResponse(saved);
    }

    public RouteResponse update(Long id, UpdateRouteRequest request) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        routeMapper.updateEntity(request, route);
        Route updated = routeRepository.save(route);
        return routeMapper.toResponse(updated);
    }

    public void delete(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RuntimeException("Route not found with id: " + id);
        }
        routeRepository.deleteById(id);
    }
}
