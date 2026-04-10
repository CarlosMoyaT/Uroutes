package com.trailsspain.api.controller;

import com.trailsspain.api.dto.request.CreateRegionRequest;
import com.trailsspain.api.dto.request.UpdateRegionRequest;
import com.trailsspain.api.dto.response.RegionResponse;
import com.trailsspain.application.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/{id}")
    public ResponseEntity<RegionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(regionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RegionResponse>> findAll() {
        return ResponseEntity.ok(regionService.findAll());
    }

    @PostMapping
    public ResponseEntity<RegionResponse> create(
            @Valid @RequestBody CreateRegionRequest request) {
        RegionResponse response = regionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRegionRequest request) {
        return ResponseEntity.ok(regionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
