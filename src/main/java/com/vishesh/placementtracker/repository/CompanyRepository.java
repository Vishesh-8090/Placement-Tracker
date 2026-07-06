package com.vishesh.placementtracker.repository;

import com.vishesh.placementtracker.entity.Company;
import com.vishesh.placementtracker.enums.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByNameIgnoreCase(String name);
    List<Company> findByStatus(CompanyStatus status);
    List<Company> findByNameContainingIgnoreCase(String keyword);
}
