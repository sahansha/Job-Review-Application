package JobApplication.JobApp.Controller;

import JobApplication.JobApp.Model.Review;
import JobApplication.JobApp.Service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId)
    {
        return this.reviewService.getAllReviews(companyId);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId ,@PathVariable Long id)
    {
        return this.reviewService.getReviewById(companyId,id);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addNewReview(@PathVariable Long companyId ,@RequestBody Review review)
    {
        return this.reviewService.addNewReview(companyId,review);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long companyId
            ,@PathVariable Long id,@RequestBody Review review)
    {
        return this.reviewService.updateReview(companyId,id,review);
    }
}
