package JobApplication.JobApp.Controller;

import JobApplication.JobApp.Model.Company;
import JobApplication.JobApp.Service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany()
    {
        return this.companyService.getAllCompanies();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        return this.companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<Company> addNewCompany(@RequestBody Company company)
    {
        return this.companyService.addNewCompany(company);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id,@RequestBody Company company)
    {
        return this.companyService.updateCompany(id,company);
    }
}
