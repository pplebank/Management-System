package com.pplebank.backend.service;

import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> listAllReservations() {
        return reservationRepository.findAll();
    }
    public List<Reservation> getReservationsByPropertyId (Long property_id){
        return reservationRepository.getReservationsByPropertyId(property_id);
        }
    public List<Reservation> getReservationsByClientId(Long client_id){
        return reservationRepository.getReservationsByClientId(client_id);
    }
    public Reservation getReservationById (Long id){
        return reservationRepository.getById(id);
    }
    public void modifyReservationById (Long id, Reservation reservation){
        Reservation record_to_modify = reservationRepository.getById(id);
        if (record_to_modify == null){

        } else {

        }
    }
    public Reservation createNewReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
    public void deleteReservationById (Long id){
         reservationRepository.deleteById(id);
    }
}