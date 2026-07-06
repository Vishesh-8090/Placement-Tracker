package com.vishesh.placementtracker.service.impl;

import com.vishesh.placementtracker.dto.request.CompanyRequest;
import com.vishesh.placementtracker.dto.response.CompanyResponse;
import com.vishesh.placementtracker.entity.Company;
import com.vishesh.placementtracker.exception.CompanyAlreadyExistsExecption;
import com.vishesh.placementtracker.exception.CompanyNotFoundException;
import com.vishesh.placementtracker.exception.InvalidCompanyDataException;
import com.vishesh.placementtracker.mapper.CompanyMapper;
import com.vishesh.placementtracker.repository.CompanyRepository;
import com.vishesh.placementtracker.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse createCompany(CompanyRequest request){
        if (companyRepository.existsByNameIgnoreCase(request.getName())){
            throw new CompanyAlreadyExistsExecption(
                    "Company already exists"
            );
        }

        validateApplicationDates(request);

        Company company = companyMapper.toEntity(request);

        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponse(savedCompany);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(){
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }

    @Override
    public CompanyResponse getCompanyById(Long id){
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found with id "+id));

        return companyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest request){
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found by id "+id));

        if (!company.getName().equalsIgnoreCase(request.getName())
            && companyRepository.existsByNameIgnoreCase(request.getName())){

            throw new CompanyAlreadyExistsExecption(
                    "Company with name "+request.getName()+" already exists.");
        }

        validateApplicationDates(request);

        companyMapper.updateEntity(company, request);

        Company updatedCompany = companyRepository.save(company);

        return companyMapper.toResponse(updatedCompany);
    }

    @Override
    public void deleteCompany(Long id){
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found by id "+id));

        companyRepository.delete(company);
    }

    private void validateApplicationDates(CompanyRequest request){
        if (request.getApplicationClosesAt() != null &&
                request.getApplicationOpensAt() != null &&
                request.getApplicationClosesAt()
                        .isBefore(request.getApplicationOpensAt())){

            throw new InvalidCompanyDataException(
                    "Application closing time cannot be before opening time");
        }
    }
}
