package com.vishesh.placementtracker.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private boolean success;
    private String message;
    private LocalDateTime timestamp;

    private Map<String, String> errors;
}
