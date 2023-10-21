package com.abiskar.crudApplication.Service;

import com.abiskar.crudApplication.Entities.Destination;
import com.abiskar.crudApplication.Entities.Review;
import com.abiskar.crudApplication.Repositories.DestinationRepository;
import com.abiskar.crudApplication.Repositories.ReviewRepository;
import com.abiskar.crudApplication.Requests.DestinationRequest;
import com.abiskar.crudApplication.Requests.ReviewRequest;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrudService.class);

    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public void addDestination(@NotNull DestinationRequest destinationRequest){

        Destination destination = new Destination();
        destination.setName(destinationRequest.getName());
        destination.setLocation(destinationRequest.getLocation());
        destination.setImageURL(destinationRequest.getImageURL());

        destinationRepository.save(destination);

        LOGGER.info("Destination Added: {}",destinationRequest.toString());
    }
    public void addReview(@NotNull ReviewRequest reviewRequest){
        Destination destination = destinationRepository.findByName(reviewRequest.getDestination())
                .orElseThrow(() -> new RuntimeException("Destination not found!"));

        Review review = new Review();
        review.setDestination(destination);
        review.setStars(reviewRequest.getStars());
        review.setReview(reviewRequest.getReview());

        reviewRepository.save(review);

        LOGGER.info("Review added: {}",reviewRequest.toString());
    }

    public List<Review> getAllReview(@NotNull DestinationRequest destinationRequest) {
        Destination destination = destinationRepository.findByName(destinationRequest.getName())
                .orElseThrow(() -> new EntityNotFoundException("Not found!"));

        List<Review> listOfReview = reviewRepository.findAllByDestination_Name(destinationRequest.getName());
        LOGGER.info("Fetched reviews for destination: {}", destinationRequest.getName());
        return listOfReview;
    }

    public Review updateReview(Long id,Review updatedReview){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with ID" + id + " not found."));
        
        review.setStars(updatedReview.getStars());
        review.setReview(updatedReview.getReview());

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id){
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review with ID " + id + " not found.");
        }
        reviewRepository.deleteById(id);
    }
}
