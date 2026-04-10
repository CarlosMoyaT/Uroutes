package com.trailsspain.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "elevation_gain_m")
    private Integer elevationGainM;

    @Column(name = "estimated_duration_min")
    private Integer estimatedDurationMin;

    @Column(name = "gps_coordinates", columnDefinition = "TEXT")
    private String gpsCoordinates;

    @Column(name = "start_point")
    private String startPoint;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "map_image_url")
    private String mapImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Review> reviews = new HashSet<>();

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (totalReviews == null) totalReviews = 0;
        if (averageRating == null) averageRating = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Difficulty {
        EASY, MODERATE, DIFFICULT, EXPERT
    }
}
