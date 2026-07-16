package com.vishesh.placementtracker.service;

import com.vishesh.placementtracker.dto.request.UserCompanyRequest;
import com.vishesh.placementtracker.dto.response.UserCompanyResponse;
import com.vishesh.placementtracker.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCompanyService {

    UserCompanyResponse apply(UserCompanyRequest request);
    Page<UserCompanyResponse> getMyApplications(Pageable pageable, ApplicationStatus status, String company);
    UserCompanyResponse getApplicationById(Long id);
    UserCompanyResponse updateApplication(Long id, UserCompanyRequest request);
    void deleteApplication(Long id);
}
