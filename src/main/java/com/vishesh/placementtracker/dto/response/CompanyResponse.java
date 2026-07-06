package com.vishesh.placementtracker.dto.response;

import com.vishesh.placementtracker.enums.CompanyStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    private Long id;

    private String name;

    private String role;

    private String location;

    private BigDecimal ctc;

    private String eligibility;

    private String careerPage;

    private LocalDateTime applicationOpensAt;

    private LocalDateTime applicationClosesAt;

    private String description;

    private CompanyStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
