package com.trailsspain.api.mapper;

import com.trailsspain.api.dto.request.CreateReviewRequest;
import com.trailsspain.api.dto.response.ReviewResponse;
import com.trailsspain.domain.model.Review;
import com.trailsspain.domain.repository.RouteRepository;
import com.trailsspain.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    public ReviewResponse toResponse(Review review) {
        return ReviewResponse.from(review);
    }

    public Review toEntity(CreateReviewRequest request) {
        Review review = Review.builder()
                .rating(request.rating())
                .comment(request.comment())
                .build();

        routeRepository.findById(request.routeId()).ifPresent(review::setRoute);
        userRepository.findById(request.userId()).ifPresent(review::setUser);

        return review;
    }
}
