package vn.ghost.jobhunter.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import vn.ghost.jobhunter.domain.Company;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // post company
    public Company handleCreateCompany(Company company) {
        return this.companyRepository.save(company);
    }

    // get company
    public List<Company> fetchAllCompany() {
        return this.companyRepository.findAll();
    }

    // put
    public Company fetchCompanyById(long id) {
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            return companyOptional.get();
        }
        return null;
    }

    public Company handleUpdateCompany(Company reqCompany) {
        Company currentCompany = this.fetchCompanyById(reqCompany.getId());
        if (currentCompany != null) {
            currentCompany.setName(reqCompany.getName());
            currentCompany.setDescription(reqCompany.getDescription());
            currentCompany.setAddress(reqCompany.getAddress());
            currentCompany.setLogo(reqCompany.getLogo());
            // save update
            currentCompany = this.companyRepository.save(currentCompany);
        }
        return currentCompany;
    }

    // delete
    public void handleDeleteCompany(long id) {
        this.companyRepository.deleteById(id);
    }
}
