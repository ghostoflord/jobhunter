package vn.ghost.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.ghost.jobhunter.domain.Company;
import vn.ghost.jobhunter.service.CompanyService;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // @GetMapping("/companies")
    // public String getCompany() {
    // return "from company";
    // }
    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company ghostCompany = this.companyService.handleCreateCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body((ghostCompany));
    }

}
