package com.vishesh.placementtracker.controller;

import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.dto.response.DashboardResponse;
import com.vishesh.placementtracker.service.DashboardService;
import com.vishesh.placementtracker.util.ApiResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(){

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Dashboard retrieved successfully",
                        dashboardService.getDashboard()
                )
        );
    }
}
