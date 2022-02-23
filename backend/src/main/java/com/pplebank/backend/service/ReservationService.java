package com.pplebank.backend.service;

import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.repository.ReservationRepository;
import com.pplebank.backend.validator.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

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
    public Vector<String> modifyReservationById (Long id, Reservation requested_reservation){
        Vector<String> errors_vector = new Vector<String>();
        if(reservationRepository.findById(id).isPresent()){

            List<Reservation> present_reservations = reservationRepository.getReservationsByPropertyId(requested_reservation.getId());

            boolean is_begin_date_in_past = ReservationValidator.checkIfDateIsInThePast(requested_reservation);
            boolean are_dates_correct = ReservationValidator.checkIfBeginDateIsLaterThanEndDate(requested_reservation);
            boolean is_reservation_date_available = ReservationValidator.checkIfReservationDateIsAvailable(present_reservations, requested_reservation);

            if (is_begin_date_in_past){errors_vector.add("Reservation begins in the past.");};
            if (are_dates_correct){errors_vector.add("Begin date of reservation is later than the end of the reservation.");};
            if (!is_reservation_date_available){errors_vector.add("In this date, another reservation is present.");};

            if (!is_begin_date_in_past & !are_dates_correct & is_reservation_date_available){
                reservationRepository.findById(id).map(reservation -> {
                    reservation.setClient(requested_reservation.getClient());
                    reservation.setProperty(requested_reservation.getProperty());
                    reservation.setTotal_cost(requested_reservation.getTotal_cost());
                    reservation.setStart_date(requested_reservation.getStart_date());
                    reservation.setEnd_date(requested_reservation.getEnd_date());

                    return reservationRepository.save(reservation);
                });
            }
            return errors_vector;
        }
        errors_vector.add("Cannot find reservation with this Id");
        return errors_vector;
    }
    public Vector<String> createNewReservation(Reservation requested_reservation){
        Vector<String> errors_vector = new Vector<String>();

            List<Reservation> present_reservations = reservationRepository.getReservationsByPropertyId(requested_reservation.getId());

            boolean is_begin_date_in_past = ReservationValidator.checkIfDateIsInThePast(requested_reservation);
            boolean are_dates_correct = ReservationValidator.checkIfBeginDateIsLaterThanEndDate(requested_reservation);
            boolean is_reservation_date_available = ReservationValidator.checkIfReservationDateIsAvailable(present_reservations, requested_reservation);

            if (is_begin_date_in_past){errors_vector.add("Reservation begins in the past.");};
            if (are_dates_correct){errors_vector.add("Begin date of reservation is later than the end of the reservation.");};
            if (!is_reservation_date_available){errors_vector.add("In this date, another reservation is present.");};

            if (!is_begin_date_in_past & !are_dates_correct & is_reservation_date_available){
                reservationRepository.save(requested_reservation);
            }
            return errors_vector;
    }
    public void deleteReservationById (Long reservation_id){
         reservationRepository.deleteById(reservation_id);
    }
}