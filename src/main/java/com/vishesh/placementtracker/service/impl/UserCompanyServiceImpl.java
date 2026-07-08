package com.vishesh.placementtracker.service.impl;

import com.sun.jdi.request.DuplicateRequestException;
import com.vishesh.placementtracker.dto.request.UserCompanyRequest;
import com.vishesh.placementtracker.dto.response.UserCompanyResponse;
import com.vishesh.placementtracker.entity.Company;
import com.vishesh.placementtracker.entity.User;
import com.vishesh.placementtracker.entity.UserCompany;
import com.vishesh.placementtracker.exception.ApplicationNotFoundException;
import com.vishesh.placementtracker.exception.CompanyNotFoundException;
import com.vishesh.placementtracker.exception.DuplicateApplicationException;
import com.vishesh.placementtracker.mapper.UserCompanyMapper;
import com.vishesh.placementtracker.repository.CompanyRepository;
import com.vishesh.placementtracker.repository.UserCompanyRepository;
import com.vishesh.placementtracker.repository.UserRepository;
import com.vishesh.placementtracker.service.AuthService;
import com.vishesh.placementtracker.service.UserCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCompanyServiceImpl implements UserCompanyService {

    private final UserCompanyRepository userCompanyRepository;
    private final UserCompanyMapper userCompanyMapper;
    private final CompanyRepository companyRepository;
    private final AuthService authService;

    @Override
    @Transactional
    public UserCompanyResponse apply(UserCompanyRequest request){
        User currentUser = authService.getAuthenticatedUser();

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new CompanyNotFoundException("Company Not Found"));

        if (userCompanyRepository.existsByUserAndCompany(currentUser, company)){
            throw new DuplicateApplicationException("You have already applied to this company");
        }

        UserCompany application = userCompanyMapper.toEntity(request);
        application.setUser(currentUser);
        application.setCompany(company);
        
        UserCompany savedApplication = userCompanyRepository.save(application);

        return userCompanyMapper.toResponse(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserCompanyResponse> getMyApplications(){
        User currentUser = authService.getAuthenticatedUser();

        List<UserCompany> applications = userCompanyRepository.findAllByUser(currentUser);

        return applications.stream()
                .map(userCompanyMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserCompanyResponse getApplicationById(Long id){
        User currentUser = authService.getAuthenticatedUser();

        UserCompany application = userCompanyRepository
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new ApplicationNotFoundException("Application not found"));

        return userCompanyMapper.toResponse(application);
    }

    @Override
    @Transactional
    public UserCompanyResponse updateApplication(Long id, UserCompanyRequest request){
        User currentUser = authService.getAuthenticatedUser();

        UserCompany application = userCompanyRepository
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new ApplicationNotFoundException("Application not found"));

        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());
        application.setAppliedAt(request.getAppliedAt());

        UserCompany saveApplication = userCompanyRepository.save(application);

        return userCompanyMapper.toResponse(saveApplication);
    }

    @Override
    @Transactional
    public void deleteApplication(Long id){
        User currentUser = authService.getAuthenticatedUser();

        UserCompany application = userCompanyRepository
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new ApplicationNotFoundException("Application not found"));

        userCompanyRepository.delete(application);

    }
}
