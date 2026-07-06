package com.vishesh.placementtracker.mapper;

import com.vishesh.placementtracker.dto.request.CompanyRequest;
import com.vishesh.placementtracker.dto.response.CompanyResponse;
import com.vishesh.placementtracker.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest request){
        return Company.builder()
                .name(request.getName())
                .role(request.getRole())
                .location(request.getLocation())
                .ctc(request.getCtc())
                .eligibility(request.getEligibility())
                .careerPage(request.getCareerPage())
                .applicationOpensAt(request.getApplicationOpensAt())
                .applicationClosesAt(request.getApplicationClosesAt())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
    }

    public CompanyResponse toResponse(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .role(company.getRole())
                .location(company.getLocation())
                .ctc(company.getCtc())
                .eligibility(company.getEligibility())
                .careerPage(company.getCareerPage())
                .applicationOpensAt(company.getApplicationOpensAt())
                .applicationClosesAt(company.getApplicationClosesAt())
                .description(company.getDescription())
                .status(company.getStatus())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

    public void updateEntity(Company company, CompanyRequest request){
        company.setName(request.getName());
        company.setRole(request.getRole());
        company.setLocation(request.getLocation());
        company.setCtc(request.getCtc());
        company.setEligibility(request.getEligibility());
        company.setCareerPage(request.getCareerPage());
        company.setApplicationOpensAt(request.getApplicationOpensAt());
        company.setApplicationClosesAt(request.getApplicationClosesAt());
        company.setDescription(request.getDescription());
        company.setStatus(request.getStatus());
    }
}
