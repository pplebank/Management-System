package com.pplebank.backend.controller;

import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService  reservationService) {
        this.reservationService  = reservationService;
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<Reservation> listAllReservations() {
        return reservationService.listAllReservations();
    }

    @RequestMapping(value = "/reservations/property/{property_id}", method = RequestMethod.GET)
    public List<Reservation> listReservationsByPropertyId(@Valid @PathVariable Long property_id) {
        return reservationService.getReservationsByPropertyId(property_id);
    }

    @RequestMapping(value = "/reservations/renter/{renter_id}", method = RequestMethod.GET)
    public List<Reservation> listReservationsByRenterId(@Valid @PathVariable Long renter_id) {
        return reservationService.getReservationsByRenterId(renter_id);
    }

    @RequestMapping(value = "/reservations/client/{client_id}", method = RequestMethod.GET)
    public List<Reservation> listReservationsByClientId(@Valid @PathVariable Long client_id) {
        return reservationService.getReservationsByClientId(client_id);
    }

    @RequestMapping(value = "reservation/{id}", method = RequestMethod.GET)
    public Reservation getReservationById(@Valid @PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @RequestMapping(value = "reservation/modify/{id}", method = RequestMethod.PUT)
    public String modifyReservationById(@Valid @PathVariable Long id, Reservation reservation) {
        reservationService.modifyReservationById(id, reservation);
        return "";
    }


    @RequestMapping(value = "reservation/new", method = RequestMethod.POST)
    public Reservation createNewReservation(@Valid Reservation reservation) {
        return reservationService.createNewReservation(reservation);
    }


    @RequestMapping(value = "product/delete/{id}", method = RequestMethod.DELETE)
    public String deleteReservation(@Valid @PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return "";
    }

}
