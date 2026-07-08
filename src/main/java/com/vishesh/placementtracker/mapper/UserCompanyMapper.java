package com.vishesh.placementtracker.mapper;

import com.vishesh.placementtracker.dto.request.UserCompanyRequest;
import com.vishesh.placementtracker.dto.response.UserCompanyResponse;
import com.vishesh.placementtracker.entity.UserCompany;
import com.vishesh.placementtracker.repository.UserCompanyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCompanyMapper {

    @Autowired
    private final UserCompanyRepository userCompanyRepository;

    public UserCompany toEntity(UserCompanyRequest request){
        return UserCompany.builder()
                .status(request.getStatus())
                .notes(request.getNotes())
                .appliedAt(request.getAppliedAt())
                .build();
    }

    public UserCompanyResponse toResponse(UserCompany entity){
        return UserCompanyResponse.builder()
                .id(entity.getId())
                .companyId(entity.getCompany().getId())
                .companyName(entity.getCompany().getName())
                .role(entity.getCompany().getRole())
                .status(entity.getStatus())
                .notes(entity.getNotes())
                .appliedAt(entity.getAppliedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
