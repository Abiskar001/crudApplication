package com.abiskar.crudApplication.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DestinationRequest {
  String name;
  String location;
  String imageURL;

  public DestinationRequest(
      @JsonProperty("name") String name,
      @JsonProperty("location") String location,
      @JsonProperty("imageURL") String imageURL) {
    this.name = name;
    this.location = location;
    this.imageURL = imageURL;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  @Override
  public String toString() {
    return "DestinationRequest{"
        + "name='"
        + name
        + '\''
        + ", location='"
        + location
        + '\''
        + ", imageURL='"
        + imageURL
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DestinationRequest that = (DestinationRequest) o;

    if (!Objects.equals(name, that.name)) return false;
    if (!Objects.equals(location, that.location)) return false;
      return Objects.equals(imageURL, that.imageURL);
  }
}
