package JobApplication.JobApp.Service;

import JobApplication.JobApp.DAO.ReviewRepository;
import JobApplication.JobApp.Model.Company;
import JobApplication.JobApp.Model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewService(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    public ResponseEntity<List<Review>> getAllReviews(Long companyId) {
        try{
            List<Review> reviews=this.reviewRepository.findByCompanyId(companyId)
                    .orElseThrow(()->new RuntimeException(String.format("There is no Review with following company id %d",companyId)));
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Review> getReviewById(Long companyId,Long id) {
        try{
            Review review=this.reviewRepository.findByCompanyIdAndId(companyId,id)
                    .orElseThrow(()->new RuntimeException(String.format("Review with id %d is not found",id)));
            return new ResponseEntity<>(review,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Review> addNewReview(Long companyId,Review review) {
        try{
            Company company=this.companyService.getCompanyById(companyId).getBody();
            review.setCompany(company);
            Review addedReview=this.reviewRepository.save(review);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(addedReview.getId()).toUri();
            return ResponseEntity.created(location).body(addedReview);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Review> updateReview(Long companyId,Long id, Review review) {
        try{
//            Review oldReview=this.reviewRepository.findByCompanyIdAndId(companyId,id)
//                    .orElseThrow(()->new RuntimeException(String.format("Review with id %d is not found in company with id %d",id,companyId)));
//            oldReview.setDescription(review.getDescription());
//            oldReview.setTitle(review.getTitle());
//            oldReview.setRating(review.getRating());
//           // oldReview.setCompany(review.getCompany());
            Company company=this.companyService.getCompanyById(companyId).getBody();
            review.setCompany(company);
            review.setId(id);
            Review updatedReview=this.reviewRepository.save(review);
            return new ResponseEntity<>(updatedReview,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
