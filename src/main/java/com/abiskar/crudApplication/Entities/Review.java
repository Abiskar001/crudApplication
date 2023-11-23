package com.abiskar.crudApplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_id", referencedColumnName = "destination_id", nullable = false)
  @JsonIgnore
  private Destination destination;

  @Column(name = "stars")
  private int stars;

  @Column(name = "review")
  private String review;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Destination getDestination() {
    return destination;
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Review review1 = (Review) o;

    if (id != review1.id) return false;
    if (stars != review1.stars) return false;
    if (!Objects.equals(destination, review1.destination)) return false;
      return Objects.equals(review, review1.review);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (destination != null ? destination.hashCode() : 0);
    result = 31 * result + stars;
    result = 31 * result + (review != null ? review.hashCode() : 0);
    return result;
  }
}
