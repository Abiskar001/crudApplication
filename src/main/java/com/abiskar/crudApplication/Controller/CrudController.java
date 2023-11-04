package com.abiskar.crudApplication.Controller;
import com.abiskar.crudApplication.Entities.Review;
import com.abiskar.crudApplication.Requests.DestinationRequest;
import com.abiskar.crudApplication.Requests.ReviewRequest;
import com.abiskar.crudApplication.Service.CrudService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrudController {

    CrudService crudService;
    @Autowired

    public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/insertDestination")
    void insertDestination(@RequestBody DestinationRequest destinationRequest){
        crudService.addDestination(destinationRequest);
    }
    @PostMapping("/insertReview")
    void insertReview(@RequestBody ReviewRequest reviewRequest){
        crudService.addReview(reviewRequest);
    }

    @GetMapping("/getAllReviewsOf")
    List<Review> getAllReviews(DestinationRequest destinationRequest){
        return crudService.getAllReview(destinationRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReviewData) {
        Review updatedReview = crudService.updateReview(id, updatedReviewData);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("delete{id}")
    void deleteReview(@PathVariable Long id){
        crudService.deleteReview(id);
    }

}
