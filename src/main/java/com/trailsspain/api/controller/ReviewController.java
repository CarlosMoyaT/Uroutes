package com.trailsspain.api.controller;

import com.trailsspain.api.dto.request.CreateReviewRequest;
import com.trailsspain.api.dto.response.ReviewResponse;
import com.trailsspain.application.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<Page<ReviewResponse>> findByRouteId(
            @PathVariable Long routeId,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(reviewService.findByRouteId(routeId, pageable));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> create(
            @Valid @RequestBody CreateReviewRequest request) {
        ReviewResponse response = reviewService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
