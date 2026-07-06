package com.vishesh.placementtracker.controller;

import com.vishesh.placementtracker.dto.request.CompanyRequest;
import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.dto.response.CompanyResponse;
import com.vishesh.placementtracker.service.CompanyService;
import com.vishesh.placementtracker.util.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(
            @Valid @RequestBody CompanyRequest request){

        CompanyResponse company = companyService.createCompany(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success(
                        "Company created successfully.",
                        company
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAllCompanies(){

        List<CompanyResponse> company = companyService.getAllCompanies();
        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Companies fetched successfully.",
                        company
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyById(
            @PathVariable Long id){

        CompanyResponse company = companyService.getCompanyById(id);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Company fetched successfully.",
                        company
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request){

        CompanyResponse company = companyService.updateCompany(id, request);
        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Company updated successfully.",
                        company
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(
            @PathVariable Long id){

        companyService.deleteCompany(id);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Company deleted successfully."
                )
        );
    }
}
