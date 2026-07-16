package com.vishesh.placementtracker.dto.response;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long totalApplications;

    private long applied;

    private long oa;

    private long interview;

    private long offers;

    private long rejected;

    private long withdrawn;

    private List<RecentApplicationResponse> recentApplications;
}
