package com.abiskar.crudApplication.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.abiskar.crudApplication.Entities.Destination;
import com.abiskar.crudApplication.Entities.Review;
import com.abiskar.crudApplication.Repositories.DestinationRepository;
import com.abiskar.crudApplication.Repositories.ReviewRepository;
import com.abiskar.crudApplication.Requests.DestinationRequest;
import com.abiskar.crudApplication.Requests.ReviewRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CrudServiceTest {

  @MockBean
  private DestinationRepository destinationRepository;

  @MockBean
  private ReviewRepository reviewRepository;

  @Autowired
  private CrudService crudService;

  @BeforeEach
  void setUp() {
    Mockito.reset(destinationRepository, reviewRepository);
  }

  @Test
  public void shouldAddDestinationSuccessfully() {
    DestinationRequest destinationRequest =
            new DestinationRequest("Test Destination", "Test Location", "Test Image URL");
    Destination destination = new Destination();
    destination.setName(destinationRequest.getName());
    destination.setLocation(destinationRequest.getLocation());
    destination.setImageURL(destinationRequest.getImageURL());
    Mockito.when(destinationRepository.save(any(Destination.class)))
            .thenReturn(destination);

    Destination addedDestination = crudService.addDestination(destinationRequest);

    assertEquals(destination, addedDestination);
  }

  @Test
  public void shouldAddReviewSuccessfully() {
    ReviewRequest reviewRequest = new ReviewRequest("Test Destination", 5, "Test Review");
    Destination mockDestination = Mockito.mock(Destination.class);

    Mockito.when(destinationRepository.findByName(reviewRequest.getDestination()))
            .thenReturn(mockDestination);

    Review mockReview = new Review();
    mockReview.setDestination(mockDestination);
    mockReview.setReview(reviewRequest.getReview());
    mockReview.setStars(reviewRequest.getStars());

    when(reviewRepository.save(Mockito.any(Review.class)))
            .thenReturn(mockReview);

    Review addedReview = crudService.addReview(reviewRequest);

    assertEquals(mockReview, addedReview);
    Mockito.verify(destinationRepository).findByName(reviewRequest.getDestination());
    Mockito.verify(reviewRepository).save(Mockito.any(Review.class));
  }

  @Test
  public void shouldGetAllReviewsSuccessfully() {
    DestinationRequest destinationRequest = new DestinationRequest("Test Destination", "", "");
    Destination mockDestination = Mockito.mock(Destination.class);
    List<Review> expectedReviews = Arrays.asList(new Review(), new Review(), new Review());

    Mockito.when(destinationRepository.findByName(destinationRequest.getName()))
            .thenReturn(mockDestination);
    Mockito.when(reviewRepository.findAllByDestination_Name(destinationRequest.getName()))
            .thenReturn(expectedReviews);

    List<Review> result = crudService.getAllReview(destinationRequest);

    assertEquals(expectedReviews, result);
    Mockito.verify(destinationRepository).findByName(destinationRequest.getName());
    Mockito.verify(reviewRepository).findAllByDestination_Name(destinationRequest.getName());
  }

  @Test
  public void shouldUpdateReviewSuccessfully() {
    Long id = 1L;
    Review expectedReview = new Review();
    expectedReview.setStars(2);
    expectedReview.setReview("hi");

    Mockito.when(reviewRepository.findById(anyLong()))
            .thenReturn(Optional.of(expectedReview));

    Mockito.when(reviewRepository.save(any(Review.class))).thenReturn(expectedReview);

    Review updatedReview = crudService.updateReview(id, expectedReview);

    assertEquals(expectedReview, updatedReview);

    Mockito.verify(reviewRepository).findById(id);
    Mockito.verify(reviewRepository).save(expectedReview);
  }

  @Test
  public void shouldDeleteReviewSuccessfully() {
    Long idToDelete = 1L;

    Mockito.when(reviewRepository.existsById(idToDelete)).thenReturn(true);

    // Act
    crudService.deleteReview(idToDelete);

    // Assert
    Mockito.verify(reviewRepository, Mockito.times(1)).deleteById(idToDelete);
  }
}

