package com.trailsspain.api.dto.response;

import com.trailsspain.domain.model.Review;
import java.time.LocalDateTime;

public record ReviewResponse(
    Long id,
    Long routeId,
    UserSummaryResponse user,
    Integer rating,
    String comment,
    LocalDateTime createdAt
) {
    public record UserSummaryResponse(Long id, String fullName, String profileImageUrl) {
        public static UserSummaryResponse from(Review review) {
            return review.getUser() != null
                ? new UserSummaryResponse(
                    review.getUser().getId(),
                    review.getUser().getFullName(),
                    review.getUser().getProfileImageUrl()
                )
                : null;
        }
    }

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
            review.getId(),
            review.getRoute() != null ? review.getRoute().getId() : null,
            UserSummaryResponse.from(review),
            review.getRating(),
            review.getComment(),
            review.getCreatedAt()
        );
    }
}
