package com.trailsspain.domain.repository;

import com.trailsspain.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByRouteId(Long routeId, Pageable pageable);
    List<Review> findByUserId(Long userId);
    boolean existsByRouteIdAndUserId(Long routeId, Long userId);
}
