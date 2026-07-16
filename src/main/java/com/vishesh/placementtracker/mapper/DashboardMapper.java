package com.vishesh.placementtracker.mapper;

import com.vishesh.placementtracker.dto.response.DashboardResponse;
import com.vishesh.placementtracker.dto.response.RecentApplicationResponse;
import com.vishesh.placementtracker.entity.UserCompany;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardMapper {

    public RecentApplicationResponse toRecentApplicationResponse(UserCompany userCompany){

        return RecentApplicationResponse.builder()
                .id(userCompany.getId())
                .companyName(userCompany.getCompany().getName())
                .status(userCompany.getStatus())
                .appliedAt(userCompany.getAppliedAt())
                .build();
    }

    public List<RecentApplicationResponse> toRecentApplicationResponse(List<UserCompany> applications){

        return applications.stream()
                .map(this::toRecentApplicationResponse)
                .toList();
    }

    public DashboardResponse toDashboardResponse(
            long totalApplications,
            long applied,
            long oa,
            long interview,
            long offers,
            long rejected,
            long withdrawn,
            List<RecentApplicationResponse> recentApplications) {

        return DashboardResponse.builder()
                .totalApplications(totalApplications)
                .applied(applied)
                .oa(oa)
                .interview(interview)
                .offers(offers)
                .rejected(rejected)
                .withdrawn(withdrawn)
                .recentApplications(recentApplications)
                .build();

    }
}
