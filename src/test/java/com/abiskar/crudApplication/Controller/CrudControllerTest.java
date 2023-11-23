package com.abiskar.crudApplication.Controller;

import static org.junit.jupiter.api.Assertions.*;

import com.abiskar.crudApplication.Entities.Review;
import com.abiskar.crudApplication.Requests.DestinationRequest;
import com.abiskar.crudApplication.Requests.ReviewRequest;
import com.abiskar.crudApplication.Service.CrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class CrudControllerTest {
  @MockBean private CrudService crudService;

  @InjectMocks private CrudController crudController;

  private MockMvc mockMvc;

  @Test
  public void testInsertDestination() throws Exception {
    MockitoAnnotations.openMocks(this);

    DestinationRequest destinationRequest = new DestinationRequest("", "", "");

    mockMvc = MockMvcBuilders.standaloneSetup(crudController).build();
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/insertDestination")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(destinationRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(crudService).addDestination(destinationRequest);
  }

  @Test
  public void testInsertReview() throws Exception {
    MockitoAnnotations.openMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(crudController).build();

    ReviewRequest reviewRequest = new ReviewRequest("", 5, "");

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/insertReview")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reviewRequest)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(crudService).addReview(reviewRequest);
  }

  @Test
  void testGetAllReview() throws Exception {
    MockitoAnnotations.openMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(crudController).build();

    DestinationRequest destinationRequest = new DestinationRequest("", "", "");

    List<Review> mockReviews = Collections.singletonList(new Review());
    Mockito.when(crudService.getAllReview(destinationRequest)).thenReturn(mockReviews);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/getAllReviewsOf")
                .param("name", destinationRequest.getName())
                .param("location", destinationRequest.getLocation())
                .param("imageURL", destinationRequest.getImageURL()))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(crudService).getAllReview(destinationRequest);
  }

  @Test
  void testUpdateReview() throws Exception {
    MockitoAnnotations.openMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(crudController).build();

    long reviewId = 1L;
    Review updatedReviewData = new Review();

    Mockito.when(crudService.updateReview(reviewId, updatedReviewData))
        .thenReturn(updatedReviewData);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/update/{id}", reviewId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedReviewData)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Mockito.verify(crudService).updateReview(reviewId, updatedReviewData);
  }

  @Test
  public void testDeleteReview() throws Exception {
    MockitoAnnotations.openMocks(this);
    long id = 45L;

    mockMvc = MockMvcBuilders.standaloneSetup(crudController).build();
    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/delete/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(crudService).deleteReview(id);
  }

  private String asJsonString(Object obj) throws Exception {
    return new ObjectMapper().writeValueAsString(obj);
  }
}
