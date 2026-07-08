package com.vishesh.placementtracker.dto.response;

import com.vishesh.placementtracker.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCompanyResponse {

    private Long id;

    private Long companyId;

    private String companyName;

    private String role;

    private ApplicationStatus status;

    private String notes;

    private LocalDateTime appliedAt;

    private LocalDateTime createdAt;
}
