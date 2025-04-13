package org.app.companyservice.company;

import org.app.companyservice.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
    void createCompanies(List<Company> companies);
    void updateCompanyRating(ReviewMessage reviewMessage);

}
