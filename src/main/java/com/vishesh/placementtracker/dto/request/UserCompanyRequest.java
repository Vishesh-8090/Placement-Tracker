package com.vishesh.placementtracker.dto.request;

import com.vishesh.placementtracker.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCompanyRequest {

    @NotNull(message = "Company Id is required")
    private Long companyId;

    @NotNull(message = "Application status is required")
    private ApplicationStatus status;

    @Size(max = 1000)
    private String notes;

    private LocalDateTime appliedAt;
}
