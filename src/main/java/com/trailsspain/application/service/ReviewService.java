package com.trailsspain.application.service;

import com.trailsspain.api.dto.request.CreateReviewRequest;
import com.trailsspain.api.dto.response.ReviewResponse;
import com.trailsspain.domain.model.Review;
import com.trailsspain.domain.repository.ReviewRepository;
import com.trailsspain.domain.repository.RouteRepository;
import com.trailsspain.api.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RouteRepository routeRepository;
    private final ReviewMapper reviewMapper;

    @Transactional(readOnly = true)
    public ReviewResponse findById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> findByRouteId(Long routeId, Pageable pageable) {
        return reviewRepository.findByRouteId(routeId, pageable).map(reviewMapper::toResponse);
    }

    public ReviewResponse create(CreateReviewRequest request) {
        if (reviewRepository.existsByRouteIdAndUserId(request.routeId(), request.userId())) {
            throw new RuntimeException("User has already reviewed this route");
        }
        Review review = reviewMapper.toEntity(request);
        Review saved = reviewRepository.save(review);
        return reviewMapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
