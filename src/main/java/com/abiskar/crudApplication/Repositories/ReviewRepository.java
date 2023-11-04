package com.abiskar.crudApplication.Repositories;

import com.abiskar.crudApplication.Entities.Destination;
import com.abiskar.crudApplication.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findAllByDestination_Name(String destinationName);
}
