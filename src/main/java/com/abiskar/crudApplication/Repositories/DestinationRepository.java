package com.abiskar.crudApplication.Repositories;

import com.abiskar.crudApplication.Entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
  Destination findByName(String name);
}
