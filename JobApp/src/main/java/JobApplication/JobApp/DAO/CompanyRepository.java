package JobApplication.JobApp.DAO;

import JobApplication.JobApp.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
