package com.pplebank.backend.repository;

import com.pplebank.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getReservationsByPropertyId(Long property_id);
    List<Reservation> getReservationsByClientId(Long client_id);
}
