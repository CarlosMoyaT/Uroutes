package com.trailsspain.domain.repository;

import com.trailsspain.domain.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    
    Page<Route> findByRegionId(Long regionId, Pageable pageable);
    
    Page<Route> findByAuthorId(Long authorId, Pageable pageable);
    
    @Query("SELECT r FROM Route r WHERE r.difficulty = :difficulty")
    Page<Route> findByDifficulty(@Param("difficulty") Route.Difficulty difficulty, Pageable pageable);
    
    @Query("SELECT r FROM Route r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Route> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT r FROM Route r WHERE r.distanceKm BETWEEN :minDistance AND :maxDistance")
    Page<Route> findByDistanceRange(@Param("minDistance") Double minDistance, @Param("maxDistance") Double maxDistance, Pageable pageable);
}
