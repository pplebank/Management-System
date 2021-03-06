package com.pplebank.backend.repository;

import com.pplebank.backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> getPropertyByRenterId(Long renter_id);

}
