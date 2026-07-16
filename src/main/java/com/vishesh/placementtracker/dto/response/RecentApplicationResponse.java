package com.vishesh.placementtracker.dto.response;

import com.vishesh.placementtracker.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentApplicationResponse {

    private Long id;

    private String companyName;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
