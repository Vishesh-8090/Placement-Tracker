package com.vishesh.placementtracker.service.impl;

import com.vishesh.placementtracker.dto.response.DashboardResponse;
import com.vishesh.placementtracker.dto.response.RecentApplicationResponse;
import com.vishesh.placementtracker.entity.User;
import com.vishesh.placementtracker.entity.UserCompany;
import com.vishesh.placementtracker.enums.ApplicationStatus;
import com.vishesh.placementtracker.mapper.DashboardMapper;
import com.vishesh.placementtracker.repository.UserCompanyRepository;
import com.vishesh.placementtracker.service.AuthService;
import com.vishesh.placementtracker.service.DashboardService;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserCompanyRepository userCompanyRepository;
    private final AuthService authService;
    private final DashboardMapper dashboardMapper;

    @Override
    public DashboardResponse getDashboard(){

        User user = authService.getAuthenticatedUser();

        long totalApplications = userCompanyRepository.countByUser(user);

        long applied = userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.APPLIED);

        long oa = userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.OA_CLEARED)
                + userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.OA_FAILED)
                + userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.OA_SCHEDULED);

        long interview = userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.INTERVIEW_CLEARED)
                + userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.INTERVIEW_FAILED)
                + userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.INTERVIEW_SCHEDULED);

        long offers = userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.OFFER_RECEIVED);

        long rejected = userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.REJECTED);

        long withdrawn = userCompanyRepository.countByUserAndStatus(user, ApplicationStatus.WITHDRAWN);

        List<UserCompany> recent = userCompanyRepository.findTop5ByUserOrderByAppliedAtDesc(user);

        List<RecentApplicationResponse> recentResponses = dashboardMapper.toRecentApplicationResponse(recent);

        return dashboardMapper.toDashboardResponse(
                totalApplications,
                applied,
                oa,
                interview,
                offers,
                rejected,
                withdrawn,
                recentResponses
        );
    }
}
