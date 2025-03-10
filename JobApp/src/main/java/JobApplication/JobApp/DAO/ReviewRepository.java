package JobApplication.JobApp.DAO;

import JobApplication.JobApp.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    Optional<List<Review>> findByCompanyId(Long id);

    Optional<Review> findByCompanyIdAndId(Long companyId, Long id);
}
