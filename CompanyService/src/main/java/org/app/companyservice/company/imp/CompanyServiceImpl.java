package org.app.companyservice.company.imp;

import lombok.AllArgsConstructor;
import org.app.companyservice.company.Company;
import org.app.companyservice.company.CompanyRepository;
import org.app.companyservice.company.CompanyService;
import org.app.companyservice.company.clients.ReviewClient;
import org.app.companyservice.company.dto.ReviewMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company companyToUpdate = optionalCompany.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyRepository.save(companyToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
      companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompanies(List<Company> companies) {
        for (Company company : companies) {
            createCompany(company);
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
       System.out.println(reviewMessage.getDescription());
       Company company = getCompanyById(reviewMessage.getCompanyId());
       double averageRating=reviewClient.getAverageRatingCompany(reviewMessage.getCompanyId());
       company.setRating(averageRating);
       companyRepository.save(company);
    }
}
