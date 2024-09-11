package vn.ghost.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import vn.ghost.jobhunter.domain.Company;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.dto.Meta;
import vn.ghost.jobhunter.domain.dto.ResultPaginationDTO;
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
    public ResultPaginationDTO handleGetCompany(Pageable pageable) {
        Page<Company> pCompany = this.companyRepository.findAll(pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Meta mt = new Meta();

        mt.setPage(pCompany.getNumber() + 1);
        mt.setPageSize(pCompany.getSize());

        mt.setPages(pCompany.getTotalPages());
        mt.setTotal(pCompany.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pCompany.getContent());
        return rs;
    }

    // put
    public Company handleUpdateCompany(Company c) {
        Optional<Company> companyOptional = this.companyRepository.findById(c.getId());
        if (companyOptional.isPresent()) {
            Company currentCompany = companyOptional.get();
            currentCompany.setLogo(c.getLogo());
            currentCompany.setName(c.getName());
            currentCompany.setDescription(c.getDescription());
            currentCompany.setAddress(c.getAddress());
            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    // delete
    public void handleDeleteCompany(long id) {
        this.companyRepository.deleteById(id);
    }
}
