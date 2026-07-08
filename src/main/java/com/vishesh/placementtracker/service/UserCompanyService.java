package com.vishesh.placementtracker.service;

import com.vishesh.placementtracker.dto.request.UserCompanyRequest;
import com.vishesh.placementtracker.dto.response.UserCompanyResponse;

import java.util.LinkedList;
import java.util.List;

public interface UserCompanyService {

    UserCompanyResponse apply(UserCompanyRequest request);
    List<UserCompanyResponse> getMyApplications();
    UserCompanyResponse getApplicationById(Long id);
    UserCompanyResponse updateApplication(Long id, UserCompanyRequest request);
    void deleteApplication(Long id);
}
