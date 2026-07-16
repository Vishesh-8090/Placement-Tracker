package com.vishesh.placementtracker.controller;

import com.vishesh.placementtracker.dto.request.UserCompanyRequest;
import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.dto.response.UserCompanyResponse;
import com.vishesh.placementtracker.enums.ApplicationStatus;
import com.vishesh.placementtracker.service.UserCompanyService;
import com.vishesh.placementtracker.util.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserCompanyResponse>> apply(
            @Valid @RequestBody UserCompanyRequest request){

        UserCompanyResponse response = userCompanyService.apply(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success(
                        "Application submitted successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserCompanyResponse>>> getMyApplications(
            @PageableDefault(
                    size = 6,
                    sort = "appliedAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) String company
            ){

        Page<UserCompanyResponse> applications = userCompanyService.getMyApplications(
                pageable,
                status,
                company
        );

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Applications retrieved successfully",
                        applications
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserCompanyResponse>> getApplicationById(
            @PathVariable Long id){

        UserCompanyResponse response = userCompanyService.getApplicationById(id);

        return ResponseEntity.ok(ApiResponseBuilder.success(
                "Application retrieved successfully",
                response
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserCompanyResponse>> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody UserCompanyRequest request){

        UserCompanyResponse response = userCompanyService.updateApplication(id, request);

        return ResponseEntity.ok(ApiResponseBuilder.success(
                "Application updated successfully",
                response
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteApplication(
            @PathVariable Long id){

        userCompanyService.deleteApplication(id);

        return ResponseEntity.ok(ApiResponseBuilder.success(
                "Application deleted successfully",
                null
        ));
    }
}
