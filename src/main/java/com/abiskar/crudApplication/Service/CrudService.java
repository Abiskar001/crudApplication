package com.abiskar.crudApplication.Service;

import com.abiskar.crudApplication.Entities.Destination;
import com.abiskar.crudApplication.Entities.Review;
import com.abiskar.crudApplication.Repositories.DestinationRepository;
import com.abiskar.crudApplication.Repositories.ReviewRepository;
import com.abiskar.crudApplication.Requests.DestinationRequest;
import com.abiskar.crudApplication.Requests.ReviewRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrudService.class);

    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public Destination addDestination(@NotNull DestinationRequest destinationRequest){
        Destination destination = new Destination();
        destination.setName(destinationRequest.getName());
        destination.setLocation(destinationRequest.getLocation());
        destination.setImageURL(destinationRequest.getImageURL());
        
        var savedDestination = destinationRepository.save(destination);

        return savedDestination;
    }
    public Review addReview(@NotNull ReviewRequest reviewRequest){
        Destination destination = destinationRepository.findByName(reviewRequest.getDestination());

        Review review = new Review();
        review.setDestination(destination);
        review.setStars(reviewRequest.getStars());
        review.setReview(reviewRequest.getReview());

        LOGGER.info("Review added: {}",reviewRequest.toString());

        return reviewRepository.save(review);
    }

    public List<Review> getAllReview(@NotNull DestinationRequest destinationRequest) {
        Destination destination = destinationRepository.findByName(destinationRequest.getName());

        List<Review> listOfReview = reviewRepository.findAllByDestination_Name(destinationRequest.getName());
        LOGGER.info("Fetched reviews for destination: {}", destinationRequest.getName());
        return listOfReview;
    }

    public Review updateReview(Long id, @NotNull Review updatedReview){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with ID" + id + " not found."));

        System.out.println("Before save: " + review);

        review.setStars(updatedReview.getStars());
        review.setReview(updatedReview.getReview());

        Review savedReview = reviewRepository.save(review);

        System.out.println("After save: " + savedReview);

        return savedReview;
    }

    public void deleteReview(Long id){

        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review with ID " + id + " not found.");
        }
        reviewRepository.deleteById(id);
    }
}
