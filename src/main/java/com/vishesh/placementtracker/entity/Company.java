package com.vishesh.placementtracker.entity;

import com.vishesh.placementtracker.enums.CompanyStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String role;

    @Column(length = 100)
    private String location;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal ctc;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String eligibility;

    @Column(length = 500)
    private String careerPage;

    @Column(name = "opens_at")
    private LocalDateTime applicationOpensAt;

    @Column(name = "closes_at")
    private LocalDateTime applicationClosesAt;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
