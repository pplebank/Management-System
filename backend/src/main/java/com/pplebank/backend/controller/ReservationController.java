package com.pplebank.backend.controller;

import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Vector;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService  reservationService) {
        this.reservationService  = reservationService;
    }

    @ApiOperation(value = "Get all reservations", response = List.class)
    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public ResponseEntity<?> listAllReservations() {
        try{
            List<Reservation> response = reservationService.listAllReservations();
            return ResponseEntity.ok(response);
        } catch (Exception error){
            return ResponseEntity.badRequest().body("error message="+ error.getMessage() + ";cause message=" + error.getCause());}
    }

    @ApiOperation(value = "Get all reservations by property", response = List.class)
    @RequestMapping(value = "/reservations/property/{property_id}", method = RequestMethod.GET)
    public ResponseEntity<?>  listReservationsByPropertyId(@Valid @PathVariable Long property_id) {
        try{
            List<Reservation> response = reservationService.getReservationsByPropertyId(property_id);
            return ResponseEntity.ok(response);
        } catch (Exception error){
            return ResponseEntity.badRequest().body("error message="+ error.getMessage() + ";cause message=" + error.getCause());}
    }

    @ApiOperation(value = "Get all reservations by client", response = List.class)
    @RequestMapping(value = "/reservations/client/{client_id}", method = RequestMethod.GET)
    public ResponseEntity<?>  listReservationsByClientId(@Valid @PathVariable Long client_id) {
        try{
        List<Reservation> response = reservationService.getReservationsByClientId(client_id);
        return ResponseEntity.ok(response);
        } catch (Exception error){
            return ResponseEntity.badRequest().body("error message="+ error.getMessage() + ";cause message=" + error.getCause());}
    }

    @ApiOperation(value = "Get reservation by id", response = Reservation.class)
    @RequestMapping(value = "reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationById(@Valid @PathVariable Long id) {
        try{
            Reservation response = reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
        } catch (Exception error){
            return ResponseEntity.badRequest().body("error message="+ error.getMessage() + ";cause message=" + error.getCause());}
    }

    @ApiOperation(value = "modify reservation", response = String.class)
    @RequestMapping(value = "reservation/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyReservationById(@Valid @PathVariable Long id, Reservation reservation) {
        try{
            Vector<String> errors = reservationService.modifyReservationById(id, reservation);
            if (errors.isEmpty()){
                return ResponseEntity.ok("Record successful modified");
            }
            return ResponseEntity.badRequest().body(errors.toString());
        } catch (Exception error){
            return ResponseEntity.badRequest().body("error message="+ error.getMessage() + ";cause message=" + error.getCause());}
    }

    @ApiOperation(value = "create new reservation", response = String.class)
    @RequestMapping(value = "reservation/new", method = RequestMethod.POST)
    public ResponseEntity<?> createNewReservation(@Valid Reservation reservation) {
        try {
            Vector<String> errors = reservationService.createNewReservation(reservation);
            if (errors.isEmpty()){
                return ResponseEntity.ok("Record successful created");
            }
            return ResponseEntity.badRequest().body(errors.toString());
        } catch(Exception error){
                return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());}
    }

    @ApiOperation(value = "delete reservation", response = List.class)
    @RequestMapping(value = "reservation/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReservation(@Valid @PathVariable Long id) {
        try{
            reservationService.deleteReservationById(id);
            return ResponseEntity.ok("Record successful deleted");
            } catch (Exception error){
            return ResponseEntity.badRequest().body("error message="+ error.getMessage() + ";cause message=" + error.getCause());}
    }
}
