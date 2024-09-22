package vn.ghost.jobhunter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;
import vn.ghost.jobhunter.domain.Company;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.service.CompanyService;
import vn.ghost.jobhunter.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // creater Company
    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company ghostCompany = this.companyService.handleCreateCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body((ghostCompany));
    }

    // get list Company
    @GetMapping("/companies")
    public ResponseEntity<ResultPaginationDTO> getCompany(
            @Filter Specification<Company> spec, Pageable pageable) {

        return ResponseEntity.ok(this.companyService.handleGetCompany(spec, pageable));
    }

    // update company
    @PutMapping("/companies")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        Company ghostCompany = this.companyService.handleUpdateCompany(company);
        return ResponseEntity.ok(ghostCompany);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") long id) {
        this.companyService.handleDeleteCompany(id);
        return ResponseEntity.ok("success delete user");
    }

    @GetMapping("/companies/{id}")
    @ApiMessage("fetch company by id")
    public ResponseEntity<Company> fetchCompanyById(@PathVariable("id") long id) {
        Optional<Company> cOptional = this.companyService.findById(id);
        return ResponseEntity.ok().body(cOptional.get());
    }
}
