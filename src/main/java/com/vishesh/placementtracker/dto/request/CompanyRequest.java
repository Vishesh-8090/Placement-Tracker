package com.vishesh.placementtracker.dto.request;

import com.vishesh.placementtracker.enums.CompanyStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Role is required")
    @Size(max = 100)
    private String role;

    @Size(max = 100)
    private String location;

    @NotNull(message = "CTC is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal ctc;

    @NotBlank(message = "Eligibility is required")
    private String eligibility;

    @Size(max = 500)
    private String careerPage;

    private LocalDateTime applicationOpensAt;

    private LocalDateTime applicationClosesAt;

    private String description;

    @NotNull(message = "Status is required")
    private CompanyStatus status;
}
