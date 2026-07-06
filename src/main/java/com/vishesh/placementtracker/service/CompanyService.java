package com.vishesh.placementtracker.service;

import com.vishesh.placementtracker.dto.request.CompanyRequest;
import com.vishesh.placementtracker.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest request);
    List<CompanyResponse> getAllCompanies();
    CompanyResponse getCompanyById(Long id);
    CompanyResponse updateCompany(Long id, CompanyRequest request);
    void  deleteCompany(Long id);
}
