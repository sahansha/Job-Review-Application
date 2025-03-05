package JobApplication.JobApp.Service;

import JobApplication.JobApp.DAO.CompanyRepository;
import JobApplication.JobApp.Model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<List<Company>> getAllCompanies() {
        try{
            List<Company> companies=this.companyRepository.findAll();
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Company> getCompanyById(Long id) {
        try{
            Company company=this.companyRepository.findById(id)
                    .orElseThrow(()->new RuntimeException(String.format("Company with id %d does not exists ",id)));
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Company> addNewCompany(Company company) {
        try{
            Company addedCompany=this.companyRepository.save(company);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedCompany.getId())
                    .toUri();
            return ResponseEntity.created(location).body(addedCompany);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Company> updateCompany(Long id, Company company) {
        try{
            Company oldCompany=this.companyRepository.findById(id)
                    .orElseThrow(()->new RuntimeException(String.format("Company with id %d does not exists ",id)));
            oldCompany.setName(company.getName());
            oldCompany.setDescription(company.getDescription());
            Company updatedCompany=this.companyRepository.save(oldCompany);
            return new ResponseEntity<>(updatedCompany,HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
