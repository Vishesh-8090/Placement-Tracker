package com.vishesh.placementtracker.repository;

import com.vishesh.placementtracker.entity.Company;
import com.vishesh.placementtracker.entity.User;
import com.vishesh.placementtracker.entity.UserCompany;
import com.vishesh.placementtracker.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

    List<UserCompany> findAllByUser(User user);
    List<UserCompany> findByCompany(Company company);
    Optional<UserCompany> findByIdAndUser(Long id, User user);
    Optional<UserCompany> findByUserAndCompany(User user, Company company);
    boolean existsByUserAndCompany(User user, Company company);
    long countByUser(User user);
    long countByUserAndStatus(User user, ApplicationStatus status);
    List<UserCompany> findTop5ByUserOrderByAppliedAtDesc(User user);
}
