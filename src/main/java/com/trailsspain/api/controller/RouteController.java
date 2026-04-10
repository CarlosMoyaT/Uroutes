package com.trailsspain.api.controller;

import com.trailsspain.api.dto.request.CreateRouteRequest;
import com.trailsspain.api.dto.request.UpdateRouteRequest;
import com.trailsspain.api.dto.response.RouteResponse;
import com.trailsspain.application.service.RouteService;
import com.trailsspain.domain.model.Route;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<RouteResponse>> findAll(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(routeService.findAll(pageable));
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<Page<RouteResponse>> findByRegion(
            @PathVariable Long regionId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(routeService.findByRegion(regionId, pageable));
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<Page<RouteResponse>> findByDifficulty(
            @PathVariable Route.Difficulty difficulty,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(routeService.findByDifficulty(difficulty, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RouteResponse>> search(
            @RequestParam String keyword,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(routeService.searchByKeyword(keyword, pageable));
    }

    @PostMapping
    public ResponseEntity<RouteResponse> create(
            @Valid @RequestBody CreateRouteRequest request) {
        RouteResponse response = routeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRouteRequest request) {
        return ResponseEntity.ok(routeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
