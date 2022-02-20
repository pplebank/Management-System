package com.pplebank.backend.repository;

import com.pplebank.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Reservation, Long> {

}
