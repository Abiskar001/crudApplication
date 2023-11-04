package com.abiskar.crudApplication.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewRequest {

  private String destination;
  private int stars;
  private String review;

  public ReviewRequest(
      @JsonProperty("destination") String destination,
      @JsonProperty("stars") int stars,
      @JsonProperty("review") String review) {
    this.destination = destination;
    this.stars = stars;
    this.review = review;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  @Override
  public String toString() {
    return "ReviewRequest{"
        + "destination='"
        + destination
        + '\''
        + ", stars="
        + stars
        + ", review='"
        + review
        + '\''
        + '}';
  }
}
